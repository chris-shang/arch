package com.cshang.arch.exception;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @Autowired
    private HttpServletRequest request;

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiError> handleValidationException(ValidationException exception) {
        ApiError error = new ApiError().withName(ApiErrorName.InvalidInput)
                .withMessage(exception.getMessage());
        List<ValidationError> validationErrors = exception.getValidationErrors();
        if (!CollectionUtils.isEmpty(validationErrors)) {
            error.withValidationErrors(validationErrors);
        }
        if (getIncludeStackTrace()) {
            error.setException(ApiException.getExceptionDetails(exception));
        }
        return makeErrorResponse(error);
    }

    @ExceptionHandler(ApiException.class)
    private ResponseEntity<ApiError> handleApiException(ApiException exception) {
        boolean includeStackTrace = getIncludeStackTrace();
        ApiError error = exception.toApiError(includeStackTrace);
        return makeErrorResponse(error);
    }

    private ResponseEntity<ApiError> makeErrorResponse(ApiError error) {
        ResponseEntity<ApiError> responseEntity =
                ResponseEntity.status(error.getCode()).body(error);
        // Set the headers (if any) specified in the error
        if (MapUtils.isEmpty(error.getHeaderValues())) {
            return responseEntity;
        }
        error.getHeaderValues().forEach((key, value) -> responseEntity.getHeaders()
                .put(key, Collections.singletonList(value)));
        return responseEntity;
    }

    private boolean getIncludeStackTrace() {
        return ObjectUtils.equals("true", request.getParameter("includeStackTrack"));
    }
}
