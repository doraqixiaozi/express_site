package com.express.pojo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SignUpForm {
	@Email(message="邮箱格式不正确")
	private String email;
	@Size(min=6,max=16,message="密码长度不合规")
	@Pattern(regexp = "(\\w*((\\d+[a-zA-z]+)|([a-zA-z]+\\d+))\\w*)",message="密码组成不合规")
	private String password;
	@NotNull(message="name should {items.user.null}")
	private String name;
	@NotNull(message="sex should {items.user.null}")
	private String sex;
	@NotNull(message="type should {items.user.null}")
	private String type;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	@Override
	public String toString() {
		return "SignUpForm [email=" + email + ", password=" + password + ", name=" + name + ", sex=" + sex + ", type="
				+ type + "]";
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
