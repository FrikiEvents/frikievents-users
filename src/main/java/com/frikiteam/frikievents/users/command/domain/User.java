package com.frikiteam.frikievents.users.command.domain;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import com.frikiteam.frikievents.users.contracts.commands.EditUser;
import com.frikiteam.frikievents.users.contracts.commands.RegisterUser;
import com.frikiteam.frikievents.users.contracts.events.UserEdited;
import com.frikiteam.frikievents.users.contracts.events.UserRegistered;

import java.time.Instant;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class User {
    @AggregateIdentifier
    private String userId;
    private String firstName;
    private String lastName;
    private String type;
    private String description;
    private String verified;
    private UserStatus status;

    public User() {
    }

    @CommandHandler
    public User(RegisterUser command) {
        Instant now = Instant.now();
        apply(
            new UserRegistered(
                command.getUserId(),
                command.getFirstName(),
                command.getLastName(),
                command.getType(),
                command.getDescription(),
                command.getVerified(),
                now
            )
        );
    }

    @CommandHandler
    public void handle(EditUser command) {
        Instant now = Instant.now();
        apply(
            new UserEdited(
                command.getUserId(),
                command.getFirstName(),
                command.getLastName(),
                command.getType(),
                command.getDescription(),
                command.getVerified(),
                now
            )
        );
    }

    @EventSourcingHandler
    protected void on(UserRegistered event) {
        userId = event.getUserId();
        firstName = event.getFirstName();
        lastName = event.getLastName();
        type = event.getType();
        description = event.getDescription();
        verified = event.getVerified();
        status = UserStatus.ACTIVE;
    }

    @EventSourcingHandler
    protected void on(UserEdited event) {
        firstName = event.getFirstName();
        lastName = event.getLastName();
        type = event.getType();
        description = event.getDescription();
        verified = event.getVerified();
    }
}