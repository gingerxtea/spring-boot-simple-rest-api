package me.yassu.restapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "HABIT_TRACKER")
@Getter
@Setter
public class HabitTrackerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "IS_HABIT_COMPLETED")
	private boolean isHabitCompleted;

	@Column(name = "USER_ID")
	private Long userId;

	@Column(name = "HABIT_ID")
	private Long habitId;

	@Column(name = "HABIT_DATE")
	private String habitDate;

	@Column(name = "CREATED_AT")
	private LocalDateTime createdAt;

	@Column(name = "UPDATED_AT")
	private LocalDateTime updatedAt;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY" )
	private String updatedBy;
}
