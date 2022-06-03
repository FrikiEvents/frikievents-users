package com.frikiteam.frikievents.users.command.application.dtos.response;

import lombok.Value;

@Value
public class EditUserResponse {
	private String userId;
	private String firstName;
	private String lastName;
	private String type;
	private String description;
	private String verified;
}