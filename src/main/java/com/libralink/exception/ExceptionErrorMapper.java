package com.libralink.exception;

import java.util.Date;

public class ExceptionErrorMapper {
	
	private String url;
	private String message;
	private Date now;
	
	public ExceptionErrorMapper(String url, String message, Date now) {
		super();
		this.url = url;
		this.message = message;
		this.now=now;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getNow() {
		return now;
	}
	public void setNow(Date now) {
		this.now = now;
	}
	
}