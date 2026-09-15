package me.yassu.restapi.utility.mapper;

import me.yassu.restapi.api.dto.CreateUserRequestDto;
import me.yassu.restapi.api.dto.UserDto;
import me.yassu.restapi.entity.UserEntity;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public final class UserMapper {

	private UserMapper() {
	}

	public static UserEntity toUserEntity(CreateUserRequestDto dto) {
		UserEntity userEntity = new UserEntity();
		userEntity.setFirstName(dto.firstName());
		userEntity.setLastName(dto.lastName());
		userEntity.setUserEmail(dto.email());
		LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
		userEntity.setCreatedAt(now);
		userEntity.setUpdatedAt(now);
		userEntity.setIsDeleted(false);
		return userEntity;
	}

	public static UserDto toUserDto(UserEntity userEntity) {
		return new UserDto(
				userEntity.getUserId(),
				userEntity.getUserEmail()
		);
	}
}
