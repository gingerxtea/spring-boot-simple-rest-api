package me.yassu.restapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "HABITS")
@Getter
@Setter
public class HabitEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "HABIT_ID")
	private long habitId;

	@Column(name = "HABIT_NAME")
	private String habitName;

	@Column(name = "HABIT_DESCRIPTION")
	private String habitDescription;

	@Column(name = "CREATED_AT")
	private LocalDateTime createdAt;

	@Column(name = "UPDATED_AT")
	private LocalDateTime updatedAt;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;

	@Column(name = "IS_DELETED")
	private boolean isDeleted;

	@Column(name = "DELETED_AT")
	private LocalDateTime deletedAt;

	@Column(name = "DELETED_BY")
	private String deletedBy;
}
