package me.yassu.restapi.api.dto;

public record DeleteUserRequestDto(
		Long userId,
		String updatedBy
) {
}
