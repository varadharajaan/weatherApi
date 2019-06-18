package com.sapient.newsapi.sapientNewsApi.controller;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sapient.newsapi.sapientNewsApi.exception.ExceptionResponse;
import com.sapient.newsapi.sapientNewsApi.exception.IntegerPraserException;
import com.sapient.newsapi.sapientNewsApi.exception.ValueNotFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	/**
	 * Handler for all the {@link ValueNotFoundException} that occurs during a REST
	 * call.
	 *
	 * @param e       the ValueNotFoundException
	 * @param request the WebRequest
	 * @return the ExceptionResponse
	 */
	@ExceptionHandler(ValueNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleValueNotFoundException(ValueNotFoundException ex,
			WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setStatus(HttpStatus.BAD_REQUEST);
		exceptionResponse.setErrorCode("400");
		exceptionResponse.setMessage(ex.getMessage());
		exceptionResponse.setDetails(request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Handler for all the {@link IntegerPraserException} that occurs during a REST
	 * call.
	 *
	 * @param e       the ValueNotFoundException
	 * @param request the WebRequest
	 * @return the ExceptionResponse
	 */
	@ExceptionHandler(IntegerPraserException.class)
	public final ResponseEntity<ExceptionResponse> handleIntegerParseException(IntegerPraserException ex,
			WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setStatus(HttpStatus.BAD_REQUEST);
		exceptionResponse.setErrorCode("400");
		exceptionResponse.setMessage(ex.getMessage());
		exceptionResponse.setDetails(request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionResponse errorDetails = new ExceptionResponse("Failed Method not supported: ", ex.getMessage());
		return new ResponseEntity<>(errorDetails, status);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionResponse errorDetails = new ExceptionResponse("Failed MediaType not supported: ", ex.getMessage());
		return new ResponseEntity<>(errorDetails, status);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionResponse errorDetails = new ExceptionResponse("Failed mediaType not acceptable: ", ex.getMessage());
		return new ResponseEntity<>(errorDetails, status);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ExceptionResponse errorDetails = new ExceptionResponse("Failed missing path variable: ", ex.getMessage());
		return new ResponseEntity<>(errorDetails, status);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionResponse errorDetails = new ExceptionResponse("Failed missing Parameter: ", ex.getMessage());
		return new ResponseEntity<>(errorDetails, status);
	}

	@Override
	protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionResponse errorDetails = new ExceptionResponse("Failed binding: ", ex.getMessage());
		return new ResponseEntity<>(errorDetails, status);
	}

	@Override
	protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionResponse errorDetails = new ExceptionResponse("Failed convertion: ", ex.getMessage());
		return new ResponseEntity<>(errorDetails, status);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ExceptionResponse errorDetails = new ExceptionResponse("Failed TypeMismatch: ", ex.getMessage());
		return new ResponseEntity<>(errorDetails, status);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionResponse errorDetails = new ExceptionResponse("Failed missing servlet Request part: ",
				ex.getMessage());
		return new ResponseEntity<>(errorDetails, status);
	}

	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {

		ExceptionResponse errorDetails = new ExceptionResponse("Failed bind: ", ex.getMessage());
		return new ResponseEntity<>(errorDetails, status);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ExceptionResponse errorDetails = new ExceptionResponse("Failed noHandler: ", ex.getMessage());
		return new ResponseEntity<>(errorDetails, status);
	}

	@Override
	protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex,
			HttpHeaders headers, HttpStatus status, WebRequest webRequest) {

		ExceptionResponse errorDetails = new ExceptionResponse("Failed RequestTimeout: ", ex.getMessage());
		return new ResponseEntity<>(errorDetails, status);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ExceptionResponse errorDetails = new ExceptionResponse("Failed InternalException: ", ex.getMessage());
		return new ResponseEntity<>(errorDetails, status);
	}
	

}
