package com.gm.model;

import com.google.gson.annotations.SerializedName;

/**
 * Error
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-08-05T15:46:50.037Z")
public class Error {
	@SerializedName("error")
	private ErrorType error = null;

	public ErrorType getError() {
		return error;
	}

	public void setError(ErrorType error) {
		this.error = error;
	}

}
