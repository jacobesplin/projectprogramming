package com.project;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginForm {
    @NotNull
	@Size(min=2, max=30)
	private String email;

	public String getEmail() {
        System.out.println(this.email);
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
