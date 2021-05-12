package com.project;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ApproveForm {

    @NotNull
	@Size(min=2, max=30)
	private String business;

	@NotNull
	@Size(min=2, max=30)
	private String name;

	@NotNull
	private String approvalCode;

    public String getBusiness() {
		return this.business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApprovalCode() {
		return approvalCode;
	}

	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
	}
}