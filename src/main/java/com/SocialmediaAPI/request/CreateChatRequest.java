package com.SocialmediaAPI.request;

import com.SocialmediaAPI.models.User;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CreateChatRequest {


	@Id
	private Integer userId;

	public CreateChatRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CreateChatRequest(Integer userId) {
		super();
		this.userId = userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	
}
