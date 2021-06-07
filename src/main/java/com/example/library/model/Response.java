package com.example.library.model;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class Response<T> {

	T obj;
	HttpStatus httpStatus;
	String message;
	
    public T getObject()  { 
    	return this.obj; 
    }
    
    public void setObject(T obj)  { 
    	this.obj = obj;
    }
    
    public void setHttpStatus(HttpStatus httpStatus) {
    	this.httpStatus=httpStatus;
    }
    
    public HttpStatus getHttpStatus() {
    	return httpStatus;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
}
