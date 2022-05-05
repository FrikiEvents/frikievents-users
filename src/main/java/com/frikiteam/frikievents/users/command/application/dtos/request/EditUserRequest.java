package com.frikiteam.frikievents.users.command.application.dtos.request;

import lombok.Getter;
import lombok.Setter;

public class EditUserRequest {
	private @Setter @Getter String userId;
	private @Getter String firstName;
	private @Getter String lastName;
	private @Getter String type;
	private @Getter String description;
	private @Getter String verified;
}