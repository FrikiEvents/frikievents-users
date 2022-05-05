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
public class UserViewProjection {
	private final UserViewRepository userViewRepository;
	
	public UserViewProjection(UserViewRepository userViewRepository) {
        this.userViewRepository = userViewRepository;
    }
	
	@EventHandler
    public void on(UserRegistered event, @Timestamp Instant timestamp) {
		UserView userView = new UserView(event.getUserId(), event.getFirstName(), event.getLastName(), event.getType(), event.getDescription(), event.getVerified(), UserStatus.ACTIVE.toString(), event.getOccurredOn());
		userViewRepository.save(userView);
    }
	
	@EventHandler
    public void on(UserEdited event, @Timestamp Instant timestamp) {
		Optional<UserView> userViewOptional = userViewRepository.findById(event.getUserId().toString());
		if (userViewOptional.isPresent()) {
			UserView userView = userViewOptional.get();
			userView.setFirstName(event.getFirstName());
			userView.setLastName(event.getLastName());
			userView.setType(event.getType());
			userView.setDescription(event.getDescription());
			userView.setVerified(event.getVerified());
			userView.setUpdatedAt(event.getOccurredOn());
			userViewRepository.save(userView);
		}
    }
}