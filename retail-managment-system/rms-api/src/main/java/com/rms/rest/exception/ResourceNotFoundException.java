package com.rms.rest.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceName, Integer id) {
        super(generateMessage(resourceName, id.toString()));
    }

    public ResourceNotFoundException(String resourceName, String key) {
        super(generateMessage(resourceName, key));
    }


    private static String generateMessage(String resourceName, String key) {
        StringBuilder messageBuilder = new StringBuilder(resourceName).
                append(" Resource With Key = ").append(key).append(" Not Found.");
        return messageBuilder.toString();
    }

}