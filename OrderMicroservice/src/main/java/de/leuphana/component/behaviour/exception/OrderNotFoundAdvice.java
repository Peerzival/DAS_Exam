package de.leuphana.component.behaviour.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class OrderNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(ArticleNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String orderNotFoundHandler(ArticleNotFoundException ex) {
		return ex.getMessage();
	}
}
