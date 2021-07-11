package com.project;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AppointmentForm {

	@NotNull
	@Size(min=2, max=30)
	private String email;

    @NotNull
	private String phone;

	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
    @NotNull
	private String date;


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
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
    public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	public String toString() {
		return "Email: " + this.email + "<br> Phone: " + this.phone + "<br>First Name:"+this.firstName+"<br>Last Name:"+this.lastName+"<br>Date/Time:"+this.date.replace("T", " ");
	}
}