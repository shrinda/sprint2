package com.cg.apps.tatasky.exceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cg.apps.tatasky.payload.BaseErrorResponse;
import com.cg.apps.tatasky.payload.ValidationErrorResponse;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private static Logger logger=LoggerFactory.getLogger(CustomGlobalExceptionHandler.class);
	
	
	private final String BAD_REQUEST = "BAD_REQUEST";
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> customHandleNotFound(Exception ex, WebRequest request) {
		
		BaseErrorResponse errors = new BaseErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setMessage(ex.getMessage());
		errors.setStatusCode(-1);

		logger.error("Exception :: "+ex.getMessage());
		
		return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);

	}	


	
	
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<ValidationErrorResponse> handleConstraintViolation(ConstraintViolationException ex,
			WebRequest request) {
		List<String> details = ex.getConstraintViolations().parallelStream().map(e -> e.getMessage())
				.collect(Collectors.toList());
		
		ValidationErrorResponse validationErrResp = new ValidationErrorResponse();		
		validationErrResp.setTimestamp(LocalDateTime.now());
		validationErrResp.setMessage(BAD_REQUEST);
		validationErrResp.setStatusCode(HttpStatus.BAD_REQUEST.value());
		validationErrResp.setDetails(details);
		logger.error("Exception :: "+ex.getMessage());		
		return new ResponseEntity<>(validationErrResp, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(OperationFailedException.class)
	public final ResponseEntity<?> handleOperationFailedViolation(OperationFailedException ex,
			WebRequest request) {
		
		BaseErrorResponse errors = new BaseErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setMessage(ex.getMessage());
		errors.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		logger.error("Exception :: "+ex.getMessage());
		return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
	
	}
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,HttpHeaders headers,HttpStatus status, WebRequest request)
	{
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", status.value());



		//Get all errors
		List<String> errors = ex.getBindingResult()
		.getFieldErrors()
		.stream()
		.map(x -> x.getDefaultMessage())
		.collect(Collectors.toList());



		body.put("errors", errors);


		logger.error("Exception :: "+ex.getMessage());
		return new ResponseEntity<>(body, headers, status);

	}
	
	
	
	@ExceptionHandler(UserAlreadyExistException.class)
	public final ResponseEntity<?> handleOperationFailedViolation(UserAlreadyExistException ex,
			WebRequest request) {
		
		BaseErrorResponse errors = new BaseErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setMessage(ex.getMessage());
		errors.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		logger.error("Exception :: "+ex.getMessage());
		return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
	
	}
	
	
	

	@ExceptionHandler(UserInputNullException.class)
	public final ResponseEntity<?> handleOperationFailedViolation(UserInputNullException ex,
			WebRequest request) {
		
		BaseErrorResponse errors = new BaseErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setMessage(ex.getMessage());
		errors.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		logger.error("Exception :: "+ex.getMessage());
		return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
	
	}
	

	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> customHandleForServerError(Exception ex, WebRequest request) {

		BaseErrorResponse errors = new BaseErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setMessage(ex.getMessage());
		errors.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		logger.error("Exception :: "+ex.getMessage());
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
