package com.example.accountvalidation.config;

import com.example.accountvalidation.domain.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "accountvalidator")
public class AccountValidatorConfig {

    private List<DataSource> sources;

    public List<DataSource> getSources() {
        return sources;
    }

    public void setSources(List<DataSource> sources) {
        this.sources = sources;
    }
}
