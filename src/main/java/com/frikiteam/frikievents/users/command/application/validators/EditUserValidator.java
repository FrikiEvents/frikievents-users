package com.frikiteam.frikievents.users.command.application.validators;

import org.axonframework.messaging.unitofwork.DefaultUnitOfWork;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.axonframework.modelling.command.Repository;
import org.springframework.stereotype.Component;
import com.frikiteam.frikievents.common.application.Notification;
import com.frikiteam.frikievents.users.command.application.dtos.request.EditUserRequest;
import com.frikiteam.frikievents.users.command.domain.User;

import java.util.Optional;

@Component
public class EditUserValidator {
    private final Repository<User> userRepository;

    public EditUserValidator(Repository<User> userRepository) {
        this.userRepository = userRepository;
    }

    public Notification validate(EditUserRequest editUserRequest)
    {
        Notification notification = new Notification();
        String userId = editUserRequest.getUserId().trim();
        if (userId.isEmpty()) {
            notification.addError("User id is required");
        }
        loadUserAggregate(userId);
        String firstName = editUserRequest.getFirstName().trim();
        if (firstName.isEmpty()) {
            notification.addError("User firstname is required");
        }
        String lastName = editUserRequest.getLastName().trim();
        if (lastName.isEmpty()) {
            notification.addError("User lastname is required");
        }
        String type = editUserRequest.getType().trim();
        if (type.isEmpty()) {
            notification.addError("User type is required");
        }
        String description = editUserRequest.getDescription().trim();
        if (description.isEmpty()) {
            notification.addError("User description is required");
        }
        String verified = editUserRequest.getVerified().trim();
        if (verified.isEmpty()) {
            notification.addError("User verified is required");
        }
        if (notification.hasErrors()) {
            return notification;
        }
        return notification;
    }

    private void loadUserAggregate(String userId) {
        UnitOfWork unitOfWork = null;
        try {
            unitOfWork = DefaultUnitOfWork.startAndGet(null);
            userRepository.load(userId);
            unitOfWork.commit();
        } catch (AggregateNotFoundException ex) {
            unitOfWork.commit();
            throw ex;
        } catch(Exception ex) {
            if (unitOfWork != null) unitOfWork.rollback();
        }
    }
}