package com.springboot.todoApp.springboottodoApp.dao.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Todo {

	@Id
	private String id;
	private String title;
	private String description;
	private long timestamp;
	
	public Todo() {
		this.timestamp = System.currentTimeMillis();
	};
	
	public Todo(String id, String title, String description) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.timestamp = System.currentTimeMillis();

	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
}
