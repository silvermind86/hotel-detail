package br.com.cvc.hotel.exceptions;

import br.com.cvc.hotel.models.dtos.ErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class ValidationErrorHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorDTO> argumentHandler(MethodArgumentNotValidException exception){
        List<ErrorDTO> dto = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach( e -> {
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            dto.add(new ErrorDTO(message));
        });
        return dto;
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFoundException.class)
    public ErrorDTO notFoundHandler(ObjectNotFoundException exception){
        return new ErrorDTO(exception.getMessage());
    }

    @ResponseStatus(code = HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(AdapterHttpException.class)
    public ErrorDTO adapterHandler(AdapterHttpException exception){
        return new ErrorDTO(exception.getMessage());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WrongParameterException.class)
    public ErrorDTO parameterHandler(WrongParameterException exception){
        return new ErrorDTO(exception.getMessage());
    }
}
