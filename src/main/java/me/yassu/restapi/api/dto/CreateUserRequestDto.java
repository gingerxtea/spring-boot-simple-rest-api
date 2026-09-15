package me.yassu.restapi.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserRequestDto(
		@NotBlank String firstName,
		@NotBlank String lastName,
		@NotBlank @Email String email
) {
}
