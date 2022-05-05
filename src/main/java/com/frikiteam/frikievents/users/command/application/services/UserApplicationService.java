package com.frikiteam.frikievents.users.command.application.services;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;
import com.frikiteam.frikievents.common.application.Notification;
import com.frikiteam.frikievents.common.application.Result;
import com.frikiteam.frikievents.common.application.ResultType;
import com.frikiteam.frikievents.users.command.application.dtos.request.EditUserRequest;
import com.frikiteam.frikievents.users.command.application.dtos.request.RegisterUserRequest;
import com.frikiteam.frikievents.users.command.application.dtos.response.EditUserResponse;
import com.frikiteam.frikievents.users.command.application.dtos.response.RegisterUserResponse;
import com.frikiteam.frikievents.users.command.application.validators.EditUserValidator;
import com.frikiteam.frikievents.users.command.application.validators.RegisterUserValidator;
import com.frikiteam.frikievents.users.contracts.commands.EditUser;
import com.frikiteam.frikievents.users.contracts.commands.RegisterUser;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class UserApplicationService {
    private final RegisterUserValidator registerUserValidator;
    private final EditUserValidator editUserValidator;
    private final CommandGateway commandGateway;

    public UserApplicationService(RegisterUserValidator registerUserValidator, EditUserValidator editUserValidator, CommandGateway commandGateway) {
        this.registerUserValidator = registerUserValidator;
        this.editUserValidator = editUserValidator;
        this.commandGateway = commandGateway;
    }

    public Result<RegisterUserResponse, Notification> register(RegisterUserRequest registerUserRequest) throws Exception {
        Notification notification = this.registerUserValidator.validate(registerUserRequest);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        String userId = UUID.randomUUID().toString();
        RegisterUser registerUser = new RegisterUser(
            userId,
            registerUserRequest.getFirstName().trim(),
            registerUserRequest.getLastName().trim(),
            registerUserRequest.getType().trim(),
            registerUserRequest.getDescription().trim(),
            registerUserRequest.getVerified().trim()
        );
        CompletableFuture<Object> future = commandGateway.send(registerUser);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        RegisterUserResponse registerUserResponseDto = new RegisterUserResponse(
            registerUser.getUserId(),
            registerUser.getFirstName(),
            registerUser.getLastName(),
            registerUser.getType(),
            registerUser.getDescription(),
            registerUser.getVerified()
        );
        return Result.success(registerUserResponseDto);
    }

    public Result<EditUserResponse, Notification> edit(EditUserRequest editUserRequest) throws Exception {
        Notification notification = this.editUserValidator.validate(editUserRequest);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        EditUser editUser = new EditUser(
            editUserRequest.getUserId().trim(),
            editUserRequest.getFirstName().trim(),
            editUserRequest.getLastName().trim(),
            editUserRequest.getType().trim(),
            editUserRequest.getDescription().trim(),
            editUserRequest.getVerified().trim()
        );
        CompletableFuture<Object> future = commandGateway.send(editUser);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        EditUserResponse editUserResponse = new EditUserResponse(
            editUser.getUserId(),
            editUser.getFirstName(),
            editUser.getLastName(),
            editUser.getType(),
            editUser.getDescription(),
            editUser.getVerified()
        );
        return Result.success(editUserResponse);
    }
}