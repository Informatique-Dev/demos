package com.rms.rest.exception;

public class BusinessValidationException extends RuntimeException{

    private String code;
   public BusinessValidationException(String resourceName, String key,String value, String code)
   {
       super(generateMessage(resourceName, key, value));
        this.code=code;
   }

   private static String generateMessage(String resourceName ,String key,String value)
   {
       StringBuilder messageBuilder = new StringBuilder(resourceName).
       append(" Resource With  ").append(key).append(" = ").append(value).append(" Violates Business Validation.");

       return messageBuilder.toString();
   }

    public String getCode() {
        return this.code;
    }
}
