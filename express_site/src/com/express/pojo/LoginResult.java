package com.express.pojo;

import java.util.List;

public class LoginResult {
	private User user;
	private List<User> friends;
	private List<Message> messages;
  private List<String> errors;
	public List<String> getErrors() {
	return errors;
}

public void setErrors(List<String> errors) {
	this.errors = errors;
}



	@Override
public String toString() {
	return "LoginResult [user=" + user + ", friends=" + friends + ", messages=" + messages + ", errors=" + errors + "]";
}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getFriends() {
		return friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
}
