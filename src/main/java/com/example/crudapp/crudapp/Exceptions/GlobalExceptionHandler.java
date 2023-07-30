package com.example.crudapp.crudapp.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.crudapp.crudapp.Payloads.ApiResponse;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException exception) {
		String message = exception.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false);
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

//	@ExceptionHandler(RefreshTokenException.class)
//	public ResponseEntity<ApiResponse> refreshTokenException(RefreshTokenException exception) {
//		String mesage = exception.getMessage();
//		ApiResponse apiResponse = new ApiResponse(mesage,false);
//		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
//	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globleExceptionHandler(Exception ex, WebRequest request) {
		ErrorResponse errorDetails = new ErrorResponse(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getMessage(), request.getDescription(false));
		return ResponseHandler.response(HttpStatus.BAD_REQUEST, false, errorDetails);
	}

}
