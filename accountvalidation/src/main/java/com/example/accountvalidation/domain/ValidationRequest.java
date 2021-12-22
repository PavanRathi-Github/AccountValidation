package com.example.accountvalidation.domain;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

public class ValidationRequest {

    @NotBlank(message = "account number is required")
    public String accountNumber;

    public List<String> sources;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }


    public List<String> getSources() {
        return sources;
    }

    public void setSources(List<String> sources) {
        this.sources = sources;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ValidationRequest{");
        sb.append("accountNumber='").append(accountNumber).append('\'');
        sb.append(", sources=").append(sources);
        sb.append('}');
        return sb.toString();
    }
}
