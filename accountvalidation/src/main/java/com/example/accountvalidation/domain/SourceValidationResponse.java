package com.example.accountvalidation.domain;

public class SourceValidationResponse {

    private String source;
    private Boolean isValid;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SourceValidationResponse{");
        sb.append("source='").append(source).append('\'');
        sb.append(", isValid=").append(isValid);
        sb.append('}');
        return sb.toString();
    }
}
