package me.yassu.restapi.api.dto;

public record HabitDto(
		Long habitId,
		String habitName,
		String habitDescription
) {
}
