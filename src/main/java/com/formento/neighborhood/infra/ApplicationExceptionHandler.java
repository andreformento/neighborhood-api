package com.formento.neighborhood.infra;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApplicationExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ValidationResult constraintViolation(ConstraintViolationException e) {
        LOGGER.error("Constraint validation failed: {}", e);

        final Builder<String, String> builder = ImmutableMap.builder();
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            String key = "";
            if (violation.getPropertyPath() != null) {
                key = violation.getPropertyPath().toString();
            }
            builder.put(key, violation.getMessage());
        }
        return new ValidationResult(builder.build());
    }

}
