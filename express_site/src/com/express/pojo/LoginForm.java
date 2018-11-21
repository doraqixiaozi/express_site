package com.express.pojo;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class LoginForm {
	@Email(message="邮箱格式不正确")
	private String email;
	@Size(min=6,max=16,message="密码长度不合规")
	@Pattern(regexp = "(\\w*((\\d+[a-zA-z]+)|([a-zA-z]+\\d+))\\w*)",message="密码组成不合规")
	private String password;
	@Override
	public String toString() {
		return "LoginForm [email=" + email + ", password=" + password + "]";
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
