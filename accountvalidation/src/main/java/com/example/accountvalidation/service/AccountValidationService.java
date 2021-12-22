package com.example.accountvalidation.service;

import com.example.accountvalidation.config.AccountValidatorConfig;
import com.example.accountvalidation.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class AccountValidationService {

    private static Logger logger = LoggerFactory.getLogger(AccountValidationService.class);

    @Autowired
    private AccountValidatorConfig accountValidatorConfig;

    @Autowired
    public RestTemplate restTemplate;


    public List<DataSource> getSources() {
        return accountValidatorConfig.getSources();
    }

    public ValidationResponse validateAccount(ValidationRequest validationRequest) {
        logger.info("Validation request - {}", validationRequest);
        List<DataSource> sources = accountValidatorConfig.getSources();
        if (validationRequest.getSources() != null) {
            Map<String, DataSource> sourcesMap = accountValidatorConfig.getSources()
                    .stream().collect(Collectors.toMap(d -> d.getName(), d -> d));
            sources = new ArrayList<>();
            for (String s : validationRequest.getSources()) {
                if (sourcesMap.containsKey(s)) {
                    sources.add(sourcesMap.get(s));
                } else {
                    logger.error("invalid source in request - {}, {}", s, validationRequest);
                }
            }
        }

        ValidationResponse validationResponse = new ValidationResponse();
        List<CompletableFuture<SourceValidationResponse>> responseFutures = sources.stream()
                .map(ds -> getValidationFromDataSource(ds, validationRequest.getAccountNumber()))
                .collect(Collectors.toList());

        CompletableFuture.allOf(responseFutures.toArray(new CompletableFuture[0])).join();

        List<SourceValidationResponse> sourceValidationResponseList = responseFutures.stream()
                        .map(f -> {
                            try{
                                return f.get();
                            }catch(Exception ex){
                                return null;
                            }
                        })
                        .filter(x -> x!= null)
                        .collect(Collectors.toList());

        validationResponse.setResult(sourceValidationResponseList);
        return validationResponse;
    }


    @Async
    public CompletableFuture<SourceValidationResponse> getValidationFromDataSource(DataSource ds, String accountNumber){
        DataSourceRequest request = new DataSourceRequest();
        request.setAccountNumber(accountNumber);
        ResponseEntity<DatasourceResponse> response = restTemplate.postForEntity(ds.getUrl(), request, DatasourceResponse.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            DatasourceResponse resp = response.getBody();
            SourceValidationResponse sourceValidationResponse = new SourceValidationResponse();
            sourceValidationResponse.setSource(ds.getName());
            sourceValidationResponse.setIsValid(resp.getValid());
            return CompletableFuture.completedFuture(sourceValidationResponse);

        } else {
            logger.error("Request to source [{}] failed - {}", ds, response);
            return CompletableFuture.completedFuture(null);
        }
    }
}
