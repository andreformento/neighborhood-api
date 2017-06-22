package com.formento.neighborhood.exception;

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
public class NeighborhoodExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(NeighborhoodExceptionHandler.class);

    @ExceptionHandler(NeighborhoodException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult neighborhoodException(NeighborhoodException e) {
        LOGGER.error("Bad request: {}", e);
        return new ErrorResult(ImmutableMap.<String, String>builder().put("application error", e.getMessage()).build());
    }

    @ExceptionHandler(NeighborhoodNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResult neighborhoodNotFoundException(NeighborhoodNotFoundException e) {
        LOGGER.error("Not found: {}", e);

        return new ErrorResult(ImmutableMap.<String, String>builder().put("notfound", e.getMessage()).build());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ErrorResult constraintViolation(ConstraintViolationException e) {
        LOGGER.error("Constraint validation failed: {}", e);

        final Builder<String, String> builder = ImmutableMap.builder();
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            String key = "";
            if (violation.getPropertyPath() != null) {
                key = violation.getPropertyPath().toString();
            }
            builder.put(key, violation.getMessage());
        }
        return new ErrorResult(builder.build());
    }

}
