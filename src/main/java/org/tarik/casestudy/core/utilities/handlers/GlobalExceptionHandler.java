package org.tarik.casestudy.core.utilities.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.tarik.casestudy.core.utilities.exceptions.BusinessException;
import org.tarik.casestudy.core.utilities.exceptions.ProblemDetails;
import org.tarik.casestudy.core.utilities.exceptions.ValidationProblemDetails;
import org.tarik.casestudy.services.constants.Messages;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler()
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBusinessExceptions(BusinessException businessException) {
        return businessException.getMessage();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetails handleValidException(MethodArgumentNotValidException methodArgumentNotValidException) {

        ValidationProblemDetails validationProblemDetails = new ValidationProblemDetails();

        validationProblemDetails.setMessage(Messages.VALIDATION_EXCEPTION);

        validationProblemDetails.setValidationErrors(new HashMap<String, String>());

        for (FieldError fieldError : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {

            validationProblemDetails.getValidationErrors().put(fieldError.getField(), fieldError.getDefaultMessage());

        }

        return validationProblemDetails;

    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleException(Exception exception) {

        return exception.getMessage();

    }

}
