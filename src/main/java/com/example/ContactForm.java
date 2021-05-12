package com.example;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ContactForm {

	@NotNull
	@Size(min=2, max=30)
	private String email;

    @NotNull
	private String phone;

	@NotNull
	private String password;

	public String getEmail() {
        System.out.println(this.email);
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String toString() {
		return "Person(Email: " + this.email + ", Phone: " + this.phone + ")";
	}
}