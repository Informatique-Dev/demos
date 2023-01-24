package com.rms.rest.exception;

public class UpdateBlockedException extends RuntimeException{
    private String code;

    public UpdateBlockedException(String resourceName, String value, String code) {
        super(generateMessage(resourceName, value));
        this.code = code;
    }

    private static String generateMessage(String resourceName, String value) {
        StringBuilder messageBuilder = new StringBuilder(resourceName).
                append(" Update blocked for requestStatus with code = ").append(value);
        return messageBuilder.toString();
    }
    public String getCode() {
        return this.code;
    }
}
