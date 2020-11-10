package com.example.demo.line.util.entity;

public class HttpResponse {

	private Integer statusCode;

	private String responseBody;

	public HttpResponse() {
	}

	public HttpResponse(Integer statusCode, String responseBody) {
		super();
		this.statusCode = statusCode;
		this.responseBody = responseBody;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}

}
