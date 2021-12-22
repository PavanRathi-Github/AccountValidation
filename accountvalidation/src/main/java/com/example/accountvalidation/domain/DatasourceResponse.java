package com.example.accountvalidation.domain;

public class DatasourceResponse {

    private Boolean isValid;

    public DatasourceResponse() {
    }

    public DatasourceResponse(Boolean isValid) {
        this.isValid = isValid;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DatasourceResponse{");
        sb.append("isValid=").append(isValid);
        sb.append('}');
        return sb.toString();
    }
}
