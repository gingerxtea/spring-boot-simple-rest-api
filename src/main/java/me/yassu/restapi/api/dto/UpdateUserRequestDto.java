package me.yassu.restapi.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdateUserRequestDto(
		Long userId,
		String firstName,
		String lastName,
		@Email String email,
		@NotBlank String updatedBy
) {
}
