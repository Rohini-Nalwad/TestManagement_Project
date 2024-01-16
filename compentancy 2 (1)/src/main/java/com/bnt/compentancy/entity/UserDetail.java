package com.bnt.compentancy.entity;

public class UserDetail {
	
	private String firstName;
	private String lastName;
	private String email;
	private String mobileNo;
	private String password;
	private String confirmPassword;
	private String roles;
	private Integer employeeId;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public UserDetail(String firstName, String lastName, String email, String mobileNo, String password,
			String confirmPassword, String roles, Integer employeeId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNo = mobileNo;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.roles = roles;
		this.employeeId = employeeId;
	}
	public UserDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "UserDetail [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", mobileNo="
				+ mobileNo + ", password=" + password + ", confirmPassword=" + confirmPassword + ", roles=" + roles
				+ ", employeeId=" + employeeId + "]";
	}
	
	
    
}
