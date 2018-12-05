package com.cshang.arch.exception;

import java.util.Collections;
import java.util.List;

public class ValidationException extends RuntimeException {

    private final List<ValidationError> validationErrors;

    public ValidationException(String errorMsg) {
        this(errorMsg, Collections.emptyList());
    }

    public ValidationException(String errorMsg, List<ValidationError> validationErrors) {
        super(errorMsg);
        this.validationErrors = validationErrors == null
                ? Collections.emptyList() : validationErrors;
    }

    public List<ValidationError> getValidationErrors() {
        return validationErrors;
    }
}
