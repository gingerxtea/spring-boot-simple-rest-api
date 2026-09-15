package me.yassu.restapi.api.dto;

public record CreateHabitTrackerRequestDto(
		Long habitId,
		String createdBy
) {
}
