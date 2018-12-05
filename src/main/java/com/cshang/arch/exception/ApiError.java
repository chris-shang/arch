package com.cshang.arch.exception;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.http.HttpStatus;

public class ApiError {

    private static final String ALPHA_NUM =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    private static final int MIN_RANDOM_VALUE = 0;

    private static final int MAX_RANDOM_VALUE = ALPHA_NUM.length();

    private static String generateErrorId() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            builder.append(ALPHA_NUM.charAt(ThreadLocalRandom.current()
                    .nextInt(MIN_RANDOM_VALUE, MAX_RANDOM_VALUE)));
        }
        return builder.toString();
    }

    @JsonProperty("errorCode")
    private int code = HttpStatus.BAD_REQUEST.value();
    @JsonProperty("errorName")
    private String name;
    @JsonProperty("errorMessage")
    private String message;
    @JsonProperty("exception")
    private String exception;
    @JsonProperty("detailedErrors")
    private List<ValidationError> validationErrors;
    @JsonProperty("errorId")
    private String errorId;
    @JsonProperty("headers")
    private Map<String, String> headerValues;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ApiError withCode(int errorCode) {
        setCode(errorCode);
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ApiError withName(String errorName) {
        setName(errorName);
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ApiError withMessage(String errorMessage) {
        setMessage(errorMessage);
        return this;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public ApiError withException(String exception) {
        setException(exception);
        return this;
    }

    public List<ValidationError> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(Collection<ValidationError> validationErrors) {
        if (validationErrors == null) {
            this.validationErrors = null;
        } else {
            this.validationErrors = new ArrayList<>(validationErrors);
        }
    }

    public ApiError withValidationError(ValidationError error) {
        if (validationErrors == null) {
            validationErrors = new ArrayList<>();
        }
        validationErrors.add(error);
        return this;
    }

    public ApiError withValidationErrors(Collection<ValidationError> validationErrors) {
        setValidationErrors(validationErrors);
        return this;
    }

    public Map<String, String> getHeaderValues() {
        return headerValues;
    }

    public void setHeaderValues(Map<String, String> headerValues) {
        this.headerValues = headerValues;
    }

    public ApiError withHeaderValues(Map<String, String> headerValues) {
        setHeaderValues(headerValues);
        return this;
    }

    public String getErrorId() {
        return errorId;
    }

    public void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    public ApiError withErrorId(String errorId) {
        setErrorId(errorId);
        return this;
    }

    public ApiError withErrorId() {
        return withErrorId(generateErrorId());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[ApiError(code='");
        builder.append(getCode());
        builder.append("', name='");
        builder.append(getName());
        builder.append("', message='");
        builder.append(getMessage());
        builder.append("', exception='");
        builder.append(getException());
        builder.append("', detailedErrors='");
        builder.append(getValidationErrors());
        builder.append("', errorId='");
        builder.append(getErrorId());
        builder.append("', headerValues='");
        builder.append(getHeaderValues());
        builder.append("')]");
        return builder.toString();
    }
}
