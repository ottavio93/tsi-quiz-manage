package com.tsi.quiz.payload.request;

import javax.validation.constraints.NotBlank;

public class EnableUserRequest {
	@NotBlank
	private String username;



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


}