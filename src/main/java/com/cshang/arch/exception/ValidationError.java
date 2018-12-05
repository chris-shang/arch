package com.cshang.arch.exception;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

public class ValidationError {

    private String fieldName;
    private String errorName;
    private String errorMessage;

    public ValidationError() {}

    public ValidationError(String message) {
        errorMessage = message;
    }

    public ValidationError withFieldName(String name) {
        this.fieldName = StringUtils.trimToNull(name);
        return this;
    }

    public ValidationError withErrorName(String name) {
        errorName = StringUtils.trimToNull(name);
        return this;
    }

    public ValidationError withErrorMessage(String message) {
        errorMessage = message;
        return this;
    }

    /**
     * Returns the name of the field that triggered the error. Can be null
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Returns the internal name of the error. Can be null
     */
    public String getErrorName() {
        return errorName;
    }

    /**
     * Returns the error message. Should be user friendly and never be null
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    public static List<String> getErrorMessages(Collection<ValidationError> errors) {
        if (CollectionUtils.isEmpty(errors)) {
            return Collections.emptyList();
        }
        return errors.stream().map(ValidationError::getErrorMessage)
                .collect(Collectors.toList());
    }

    public static ValidationError getInternalValidationError() {
        return new ValidationError(
                        "Generic error message when we don't have a specific error message "
                                + "to display to the user");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (fieldName != null) {
            sb.append(fieldName).append(':');
        }
        if (errorName != null) {
            sb.append(errorName).append(':');
        }
        sb.append(errorMessage);
        return sb.toString();
    }
}
