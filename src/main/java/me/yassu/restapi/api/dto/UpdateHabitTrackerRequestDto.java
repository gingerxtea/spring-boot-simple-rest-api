package me.yassu.restapi.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateHabitTrackerRequestDto(
		@NotNull Long habitTrackerId,
		boolean isHabitCompleted,
		@NotBlank String updatedBy
) {
}
