package br.com.jjflix.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage resourceNotFoundException(ResourceNotFoundException e, WebRequest request) {

	ErrorMessage message = new ErrorMessage(
		new Date(),
		HttpStatus.NOT_FOUND.value(),
		"Recurso não encontrado.",
		montarListaDeMensagens(e.getMessage()),
		null,
		request.getDescription(false));
	return message;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage methodArgumentNotValidException(MethodArgumentNotValidException e, WebRequest request) {

	List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

	List<String> mensagens = new ArrayList<String>();
	List<String> fields = new ArrayList<String>();

	fieldErrors.forEach(fieldError -> {
	    mensagens.add(fieldError.getDefaultMessage());
	    fields.add(fieldError.getField());
	});

	ErrorMessage message = new ErrorMessage(
		new Date(),
		HttpStatus.BAD_REQUEST.value(),
		"Requisição mal formatada.",
		mensagens,
		fields,
		request.getDescription(false));
	return message;
    }

    private List<String> montarListaDeMensagens(String msg) {
	List<String> mensagens = new ArrayList<String>();
	mensagens.add(msg);
	return mensagens;
    }
}
