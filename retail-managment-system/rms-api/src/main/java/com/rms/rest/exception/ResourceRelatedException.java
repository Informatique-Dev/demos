package com.rms.rest.exception;

public class ResourceRelatedException extends RuntimeException {
    private String code;

    public ResourceRelatedException(String resourceName, String key, String value, String code) {
        super(generateMessage(resourceName, key, value));
        this.code = code;
    }

    private static String generateMessage(String resourceName, String key, String value) {
        StringBuilder messageBuilder = new StringBuilder(resourceName).
                append(" Resource With ").append(key).append(" = ").append(value).append(" Has Relations.");
        return messageBuilder.toString();
    }

    public String getCode() {
        return this.code;
    }
}
