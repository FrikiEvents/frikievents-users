package com.frikiteam.frikievents.users.query.projections;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;

@Entity
public class UserHistoryView {
	@Id @GeneratedValue @Getter @Setter
	private Long userHistoryId;
	@Column(length=36) @Getter @Setter
    private String userId;
	@Column(length=100) @Getter @Setter
	private String firstName;
	@Column(length=100) @Getter @Setter
	private String lastName;
	@Column(length=1) @Getter @Setter
	private String type;
	@Column(length=100) @Getter @Setter
	private String description;
	@Column(length=5) @Getter @Setter
	private String verified;
	@Column(length=20) @Getter @Setter
	private String status;
	@Getter @Setter
	private Instant createdAt;

	public UserHistoryView() {
	}

	public UserHistoryView(String userId, String firstName, String lastName, String type, String description, String verified, String status, Instant createdAt) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.type = type;
		this.description = description;
		this.verified = verified;
		this.status = status;
		this.createdAt = createdAt;
    }

	public UserHistoryView(UserHistoryView userHistoryView) {
		this.userId = userHistoryView.getUserId();
		this.firstName = userHistoryView.getFirstName();
		this.lastName = userHistoryView.getLastName();
		this.type = userHistoryView.getType();
		this.description = userHistoryView.getDescription();
		this.verified = userHistoryView.getVerified();
		this.status = userHistoryView.getStatus();
		this.createdAt = userHistoryView.getCreatedAt();
	}
}