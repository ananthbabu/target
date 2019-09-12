package com.rest.retail.myRetail.vo.response;

public class ProductResponse {

	private int statusCode;
	private String message;

	/**
	 * @param errorCode
	 * @param message
	 */
	public ProductResponse(int statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

}
