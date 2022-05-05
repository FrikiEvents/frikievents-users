package com.frikiteam.frikievents.users.command.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.frikiteam.frikievents.common.api.ApiController;
import com.frikiteam.frikievents.common.application.Notification;
import com.frikiteam.frikievents.common.application.Result;
import com.frikiteam.frikievents.users.command.application.dtos.request.EditUserRequest;
import com.frikiteam.frikievents.users.command.application.dtos.request.RegisterUserRequest;
import com.frikiteam.frikievents.users.command.application.dtos.response.EditUserResponse;
import com.frikiteam.frikievents.users.command.application.dtos.response.RegisterUserResponse;
import com.frikiteam.frikievents.users.command.application.services.UserApplicationService;

@RestController
@RequestMapping("/users")
@Tag(name = "Users")
public class UserCommandController {
    private final UserApplicationService customerApplicationService;
    private final CommandGateway commandGateway;

    public UserCommandController(UserApplicationService customerApplicationService, CommandGateway commandGateway) {
        this.customerApplicationService = customerApplicationService;
        this.commandGateway = commandGateway;
    }

    @PostMapping(path= "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> register(@RequestBody RegisterUserRequest registerUserRequest) {
        try {
            Result<RegisterUserResponse, Notification> result = customerApplicationService.register(registerUserRequest);
            if (result.isSuccess()) {
                return ApiController.created(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch(Exception e) {
            return ApiController.serverError();
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Object> edit(@PathVariable("userId") String userId, @RequestBody EditUserRequest editUserRequest) {
        try {
            editUserRequest.setUserId(userId);
            Result<EditUserResponse, Notification> result = customerApplicationService.edit(editUserRequest);
            if (result.isSuccess()) {
                return ApiController.ok(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch (AggregateNotFoundException exception) {
            return ApiController.notFound();
        } catch(Exception e) {
            return ApiController.serverError();
        }
    }
}