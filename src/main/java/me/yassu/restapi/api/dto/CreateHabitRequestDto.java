package me.yassu.restapi.api.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateHabitRequestDto(
		@NotBlank String habitName,
		String habitDescription,
		@NotBlank String createdBy
) {
}
