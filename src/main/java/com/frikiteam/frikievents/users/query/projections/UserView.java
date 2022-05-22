package com.frikiteam.frikievents.users.query.projections;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Entity
public class UserView {
	@Id @Column(length=36) @Getter @Setter
    private String customerId;
	@Column(length=100) @Getter @Setter
	private String firstName;
	@Column(length=100) @Getter @Setter
	private String lastName;
	@Column(length=3) @Getter @Setter
	private String type;
	@Column(length=100) @Getter @Setter
	private String description;
	@Column(length=5) @Getter @Setter
	private String verified;
	@Column(length=20) @Getter @Setter
	private String status;
	private Instant createdAt;
	@Column(nullable = true) @Getter @Setter
	private Instant updatedAt;

	public UserView() {
	}

	public UserView(String customerId, String firstName, String lastName, String type, String description, String verified, String status, Instant createdAt) {
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.type = type;
		this.description = description;
		this.verified = verified;
		this.status = status;
		this.createdAt = createdAt;
    }
}