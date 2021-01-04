package com.cg.apps.tatasky.payload;

public class BaseResponse {


		private Object response;
		private int statusCode;
		private String message;
		
		public Object getResponse() {
			return response;
		}
		public void setResponse(Object response) {
			this.response = response;
		}
		public int getStatusCode() {
			return statusCode;
		}
		public void setStatusCode(int statusCode) {
			this.statusCode = statusCode;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		
		
		
		
	}


