package com.example.accountvalidation.domain;

import java.util.List;

public class ValidationResponse {

    private List<SourceValidationResponse> result;

    public List<SourceValidationResponse> getResult() {
        return result;
    }

    public void setResult(List<SourceValidationResponse> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ValidationResponse{");
        sb.append("result=").append(result);
        sb.append('}');
        return sb.toString();
    }
}
