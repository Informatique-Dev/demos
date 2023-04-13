package com.rms.rest.exception;

public class InvalidInputException extends RuntimeException{


    private String code ;
    public InvalidInputException(String resourceName, Integer value , String code) {
        super(generateMessage(resourceName, value));
        this.code = code;

    }


    private static String generateMessage(String resourceName,  Integer value ) {
        StringBuilder messageBuilder = new StringBuilder(resourceName).
                append(" Input Value ").append(value).append(" =  Not valid .");
        return messageBuilder.toString();
    }

    public String getCode() {
        return this.code;
    }










}
