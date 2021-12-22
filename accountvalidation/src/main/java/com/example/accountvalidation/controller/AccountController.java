package com.example.accountvalidation.controller;

import com.example.accountvalidation.domain.*;
import com.example.accountvalidation.service.AccountValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AccountController {

    private static Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountValidationService accountValidationService;

    @PostMapping("/validate")
    public ValidationResponse validate(@Valid @RequestBody ValidationRequest validationRequest){
        return accountValidationService.validateAccount(validationRequest);
    }


    @GetMapping("/sources")
    public List<DataSource> getSources(){
        logger.info("Sources - {}", accountValidationService.getSources());
        return accountValidationService.getSources();
    }


    @PostMapping("/testSource1")
    public DatasourceResponse testValidationSource1(@RequestBody DataSourceRequest dataSourceRequest){
        logger.info("validation request in testValidationSource1- {}", dataSourceRequest);
        return new DatasourceResponse(true);
    }
    
    @PostMapping("/testSource2")
    public DatasourceResponse testValidationSource2(@RequestBody DataSourceRequest dataSourceRequest){
        logger.info("validation request in testValidationSource2- {}", dataSourceRequest);
        return new DatasourceResponse(true);
    }
}
