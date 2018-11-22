package com.express.pojo;

import java.util.List;

public class LoginResult {
	private User user;
	private List<Friend> friends;
	private List<String> errors;

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Friend> getFriends() {
		return friends;
	}

	public void setFriends(List<Friend> friends) {
		this.friends = friends;
	}

	@Override
	public String toString() {
		return "LoginResult [user=" + user + ", friends=" + friends + ", errors=" + errors + "]";
	}

}
