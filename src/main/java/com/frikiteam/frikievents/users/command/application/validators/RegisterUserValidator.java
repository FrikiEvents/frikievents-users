package com.frikiteam.frikievents.users.command.application.validators;

import org.springframework.stereotype.Component;
import com.frikiteam.frikievents.common.application.Notification;
import com.frikiteam.frikievents.users.command.application.dtos.request.RegisterUserRequest;

@Component
public class RegisterUserValidator {
    public Notification validate(RegisterUserRequest registerUserRequest)
    {
        Notification notification = new Notification();
        String firstName = registerUserRequest.getFirstName().trim();
        if (firstName.isEmpty()) {
            notification.addError("User firstname is required");
        }
        String lastName = registerUserRequest.getLastName().trim();
        if (lastName.isEmpty()) {
            notification.addError("User lastname is required");
        }
        String type = registerUserRequest.getType().trim();
        if (type.isEmpty()) {
            notification.addError("User type is required");
        }
        String description = registerUserRequest.getDescription().trim();
        if (description.isEmpty()) {
            notification.addError("User description is required");
        }
        String verified = registerUserRequest.getVerified().trim();
        if (verified.isEmpty()) {
            notification.addError("User verified is required");
        }
        if (notification.hasErrors()) {
            return notification;
        }
        return notification;
    }
}