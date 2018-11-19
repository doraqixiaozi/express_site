package com.express.pojo;

import java.util.Date;

public class Message {
	private Integer id;
	private Integer to_id;
	private Integer from_id;
	private String text;
	private String isread;
	private Date m_time;

	@Override
	public String toString() {
		return "Message [id=" + id + ", to_id=" + to_id + ", from_id=" + from_id + ", text=" + text + ", isread="
				+ isread + ", m_time=" + m_time + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIsread() {
		return isread;
	}

	public void setIsread(String isread) {
		this.isread = isread;
	}

	public Date getM_time() {
		return m_time;
	}

	public void setM_time(Date m_time) {
		this.m_time = m_time;
	}

	public Integer getTo_id() {
		return to_id;
	}

	public void setTo_id(Integer to_id) {
		this.to_id = to_id;
	}

	public Integer getFrom_id() {
		return from_id;
	}

	public void setFrom_id(Integer from_id) {
		this.from_id = from_id;
	}
	
}
