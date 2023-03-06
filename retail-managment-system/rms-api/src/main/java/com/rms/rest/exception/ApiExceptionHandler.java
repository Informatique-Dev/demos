package com.rms.rest.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, LocalDateTime.now(), "Malformed JSON request", details);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());

        StringBuilder errorMessageBuilder = new StringBuilder();
        errorMessageBuilder.append(ex.getMethod());
        errorMessageBuilder.append(
                " method is not supported for this request. Supported methods are ");
        ex.getSupportedHttpMethods().forEach(t -> errorMessageBuilder.append(t + " "));

        ApiError apiError = new ApiError(HttpStatus.METHOD_NOT_ALLOWED, LocalDateTime.now(),
                errorMessageBuilder.toString(), details);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(
            ServletRequestBindingException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, LocalDateTime.now(), "Missing Parameters",
                details);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> details = new ArrayList<String>();
        details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getObjectName() + " : " + error.getDefaultMessage())
                .collect(Collectors.toList());

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, LocalDateTime.now(), "Validation Errors", details);

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    //custom exception handling start from here

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFoundException(
            ResourceNotFoundException ex) {

        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());

        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, LocalDateTime.now(), "Resource Not Found", details);

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    protected ResponseEntity<Object> handleResourceAlreadyExistsException(
            ResourceAlreadyExistsException ex) {

        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());

        ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getCode(), LocalDateTime.now(), "Resource Already Exists", details);

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(ResourceRelatedException.class)
    protected ResponseEntity<Object> handleResourceAlreadyExistsException(
            ResourceRelatedException ex) {

        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());

        ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getCode(), LocalDateTime.now(), "Resource Already Exists", details);

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(PaymentTypeNotValidException.class)
    protected ResponseEntity<Object> handlePaymentTypeNotValidException(
            PaymentTypeNotValidException ex) {

        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());

        ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getCode(), LocalDateTime.now(), "Payment Type not Valid", details);

        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

//    @ExceptionHandler(ResourceNotFound.class)
//    @ResponseStatus(value = HttpStatus.NOT_FOUND)
//    public @ResponseBody ApiError handleResourceNotFound(final ResourceNotFound exception,
//                                                         final HttpServletRequest request) {
//
//        ApiError error = new ApiError();
//        error.setErrorMessage(exception.getMessage());
//        error.setStatus(404);
//        error.setPath(request.getRequestURI());
//        error.setTimestamp(LocalDateTime.now());
//
//        return error;
//    }



}
