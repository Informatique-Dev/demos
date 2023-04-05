package com.rms.rest.exception;

public enum ErrorCodes {
    DUPLICATE_RESOURCE("100", "Resource already exists"),
    RELATED_RESOURCE("200", "Resource has a relations"),
    UPDATE_BLOCKED("300", "Update Blocked"),
    BUSINESS_VALIDATION("303", "Resource Violates Business Validation"),

    PAYMENT_TYPE_NOT_VALID("301", "not valid");
    private String code;
    private String desc;

    ErrorCodes(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }
}