package me.yassu.restapi.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DeleteHabitRequestDto(
		@NotNull Long habitId,
		@NotBlank String updatedBy
) {
}
