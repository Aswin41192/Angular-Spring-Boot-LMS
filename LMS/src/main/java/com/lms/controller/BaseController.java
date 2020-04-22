package com.lms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class BaseController{
	
	protected static final Logger logger = LoggerFactory.getLogger("APPLOGGER");
	
	protected  <T>  ResponseEntity<T> getResponseEntity_WithStatusOk(T t){
		return new ResponseEntity<T>(t,HttpStatus.OK);
	}
	
	protected <T>  ResponseEntity<T> getResponseEntity_WithStatusCreated(T t){
		return new ResponseEntity<T>(t,HttpStatus.CREATED);
	}
	
	protected <T>  ResponseEntity<T> getResponseEntity_WithStatusBadRequest(T t){
		return new ResponseEntity<T>(t,HttpStatus.BAD_REQUEST);
	}
}
