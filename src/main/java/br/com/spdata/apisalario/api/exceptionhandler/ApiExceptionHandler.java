package br.com.spdata.apisalario.api.exceptionhandler;

import java.time.OffsetDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.spdata.apisalario.domain.exception.SalarioInvalidoException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(SalarioInvalidoException.class)
	public ResponseEntity<Object> handleEmailJaExistente(SalarioInvalidoException ex, WebRequest request) {
		var status = HttpStatus.BAD_REQUEST;
		var problema = new Problema(status.value(), OffsetDateTime.now(), ex.getMessage());
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
}
