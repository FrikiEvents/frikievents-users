package com.frikiteam.frikievents.users.query.projections;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;
import com.frikiteam.frikievents.users.command.domain.*;
import com.frikiteam.frikievents.users.contracts.events.UserEdited;
import com.frikiteam.frikievents.users.contracts.events.UserRegistered;

import java.time.Instant;
import java.util.Optional;

@Component
public class UserHistoryViewProjection {
	private final UserHistoryViewRepository userHistoryViewRepository;

	public UserHistoryViewProjection(UserHistoryViewRepository userHistoryViewRepository) {
        this.userHistoryViewRepository = userHistoryViewRepository;
    }
	
	@EventHandler
    public void on(UserRegistered event, @Timestamp Instant timestamp) {
		UserHistoryView userHistoryView = new UserHistoryView(event.getUserId(), event.getFirstName(), event.getLastName(), event.getType(), event.getDescription(), event.getVerified(), UserStatus.ACTIVE.toString(), event.getOccurredOn());
		userHistoryViewRepository.save(userHistoryView);
    }
	
	@EventHandler
    public void on(UserEdited event, @Timestamp Instant timestamp) {
		Optional<UserHistoryView> userHistoryViewOptional = userHistoryViewRepository.getLastByUserId(event.getUserId().toString());
		if (userHistoryViewOptional.isPresent()) {
			UserHistoryView userHistoryView = userHistoryViewOptional.get();
			userHistoryView = new UserHistoryView(userHistoryView);
			userHistoryView.setFirstName(event.getFirstName());
			userHistoryView.setLastName(event.getLastName());
			userHistoryView.setType(event.getType());
			userHistoryView.setDescription(event.getDescription());
			userHistoryView.setVerified(event.getVerified());
			userHistoryView.setCreatedAt(event.getOccurredOn());
			userHistoryViewRepository.save(userHistoryView);
		}
    }
}