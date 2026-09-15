package me.yassu.restapi.api;

import me.yassu.restapi.exception.BadRequestException;
import me.yassu.restapi.exception.UserDeletedException;
import me.yassu.restapi.exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ProblemDetail handleUserNotFoundException(UserNotFoundException e) {
		logger.error("error in users: {}", e.getMessage());
		return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
	}

	@ExceptionHandler(exception = {BadRequestException.class, UserDeletedException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ProblemDetail handleBadRequestException(Exception e) {
		logger.error("error in request: {}", e.getMessage());
		return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ProblemDetail handleMethodArgumentNotValidException(
			MethodArgumentNotValidException ex) {

		ProblemDetail problemDetail =
				ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

		problemDetail.setTitle("Validation Failed");
		problemDetail.setDetail("One or more request fields are invalid.");

		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult()
				.getFieldErrors()
				.forEach(error ->
						errors.put(error.getField(), error.getDefaultMessage())
				);

		problemDetail.setProperty("errors", errors);

		return problemDetail;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ProblemDetail handleException(Exception e) {
		logger.error("error occurred: {}", e.getMessage());
		return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
	}
}
