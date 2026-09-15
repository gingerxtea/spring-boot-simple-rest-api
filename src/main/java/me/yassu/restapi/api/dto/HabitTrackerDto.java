package me.yassu.restapi.api.dto;

public record HabitTrackerDto(
		Long habitTrackerId,
		String habitName,
		String date,
		boolean isHabitCompleted
) {
}
