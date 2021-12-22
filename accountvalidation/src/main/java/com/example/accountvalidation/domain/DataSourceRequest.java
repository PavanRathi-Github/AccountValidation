package com.example.accountvalidation.domain;

public class DataSourceRequest {

    private String accountNumber;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DataSourceRequest{");
        sb.append("accountNumber='").append(accountNumber).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
