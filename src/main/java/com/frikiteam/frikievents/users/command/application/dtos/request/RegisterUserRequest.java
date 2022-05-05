package com.frikiteam.frikievents.users.command.application.dtos.request;

import lombok.Value;

@Value
public class RegisterUserRequest {
	private String firstName;
	private String lastName;
	private String type;
	private String description;
	private String verified;
}