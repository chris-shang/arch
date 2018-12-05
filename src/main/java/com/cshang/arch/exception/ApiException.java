package com.cshang.arch.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {

    private final int errorCode;
    private final String errorName;
    private List<ValidationError> validationErrors;
    private Map<String, String> headerValues;

    public ApiException(String message) {
        this(HttpStatus.BAD_REQUEST.value(), null, message, Collections.emptyList(),
                Collections.emptyMap());
    }

    public ApiException(String errorName, String message) {
        this(HttpStatus.BAD_REQUEST.value(), errorName, message, Collections
                .emptyList(), Collections.emptyMap());
    }

    public ApiException(int errorCode, String message) {
        this(errorCode, null, message, Collections.emptyList(),
                Collections.emptyMap());
    }

    public ApiException(int errorCode, String errorName, String message) {
        this(errorCode, errorName, message, Collections.emptyList(),
                Collections.emptyMap());
    }

    public ApiException(int errorCode, String errorName, String message,
                        List<ValidationError> validationErrors) {
        this(errorCode, errorName, message, validationErrors, Collections.emptyMap());
    }

    public ApiException(int errorCode, String errorName, String message,
                        List<ValidationError> validationErrors, Map<String, String> headerValues) {
        super(message);
        this.errorCode = errorCode;
        this.errorName = errorName;
        this.validationErrors = validationErrors;
        this.headerValues = headerValues;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorName() {
        return errorName;
    }

    public List<ValidationError> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<ValidationError> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public Map<String, String> getHeaderValues() {
        return headerValues;
    }

    public void setHeaderValues(Map<String, String> headerValues) {
        this.headerValues = headerValues;
    }

    public ApiError toApiError(boolean includeStacktrace) {
        ApiError error =
                new ApiError().withCode(getErrorCode()).withName(getErrorName())
                        .withMessage(getMessage()).withValidationErrors(validationErrors)
                        .withHeaderValues(headerValues).withErrorId();
        if (includeStacktrace) {
            error.setException(getExceptionDetails(this));
        }
        return error;
    }

    public static String getExceptionDetails(Throwable throwable) {
        if (throwable == null) {
            return null;
        }

        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            pw.flush();
            return sw.toString();
        } catch (RuntimeException e) {
            // swallow it
        }
        return null;
    }

}
