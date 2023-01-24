package com.rms.rest.exception;

public class ResourceAlreadyExistsException extends RuntimeException {
    private String code;

    public ResourceAlreadyExistsException(String resourceName, String key, String value, String code) {
        super(generateMessage(resourceName, key, value));
        this.code = code;
    }

    private static String generateMessage(String resourceName, String key, String value) {
        StringBuilder messageBuilder = new StringBuilder(resourceName).
                append(" Resource With ").append(key).append(" = ").append(value).append(" Already Exists.");
        return messageBuilder.toString();
    }
    public String getCode() {
        return this.code;
    }
}
