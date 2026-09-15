package me.yassu.restapi.service;

import jakarta.validation.Valid;
import me.yassu.restapi.api.dto.CreateUserRequestDto;
import me.yassu.restapi.api.dto.DeleteUserRequestDto;
import me.yassu.restapi.api.dto.UpdateUserRequestDto;
import me.yassu.restapi.api.dto.UserDto;
import me.yassu.restapi.entity.UserEntity;
import me.yassu.restapi.exception.BadRequestException;
import me.yassu.restapi.exception.UserDeletedException;
import me.yassu.restapi.exception.UserNotFoundException;
import me.yassu.restapi.repository.UserRepository;
import me.yassu.restapi.utility.mapper.UserMapper;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Transactional
	public UserDto createUser(CreateUserRequestDto dto) {
		logger.info("user service create user starts");
		UserEntity newUser = userRepository.save(UserMapper.toUserEntity(dto));
		logger.info("user service create user starts");
		return UserMapper.toUserDto(newUser);
	}

	@Transactional
	public UserDto updateUser(@Valid UpdateUserRequestDto dto) {
		if (dto == null || dto.userId() == null) {
			throw new BadRequestException("User ID is mandatory to update user info");
		}

		UserEntity user = userRepository.findById(dto.userId())
				.orElseThrow(() -> new UserNotFoundException(getUserNotFoundMessage() + dto.userId()));

		if (user.getIsDeleted() != null && user.getIsDeleted()) {
			throw new UserDeletedException("user is deleted cannot access the account");
		}

		if (dto.firstName() != null && !dto.firstName().isBlank()) {
			user.setFirstName(dto.firstName());
		}
		if (dto.lastName() != null && !dto.lastName().isBlank()) {
			user.setLastName(dto.lastName());
		}
		if (dto.email() != null && !dto.email().isBlank()) {
			user.setUserEmail(dto.email());
		}
		user.setUpdatedAt(LocalDateTime.now(ZoneOffset.UTC));
		user.setUpdatedBy(dto.updatedBy());
		UserEntity updatedUser = userRepository.save(user);
		return UserMapper.toUserDto(updatedUser);
	}

	@Transactional
	public void deleteUser(@Valid DeleteUserRequestDto dto) {
		logger.info("user service delete user ends");
		if (dto == null || dto.userId() == null) {
			throw new BadRequestException("User ID is mandatory to update user info");
		}

		UserEntity user = userRepository.findById(dto.userId())
				.orElseThrow(() -> new UserNotFoundException(getUserNotFoundMessage() + dto.userId()));

		if (user.getIsDeleted() != null && user.getIsDeleted()) {
			throw new UserDeletedException("user is already deleted cannot access the account");
		}

		user.setIsDeleted(true);
		user.setDeletedAt(LocalDateTime.now(ZoneOffset.UTC));
		user.setDeletedBy(dto.updatedBy());

		UserEntity deletedUser = userRepository.save(user);
		logger.info("user with id: {} deleted successfully", deletedUser.getUserId());
	}

	@Transactional(readOnly = true)
	public UserDto getUserById(Long userId) {
		UserEntity user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException(getUserNotFoundMessage() + userId));

		if (user.getIsDeleted() != null && user.getIsDeleted()) {
			throw new UserDeletedException("user is deleted cannot access the account");
		}

		return UserMapper.toUserDto(user);
	}

	@Transactional(readOnly = true)
	public List<UserDto> getAllUsers() {
		return userRepository.findAll().stream().filter(user -> !user.getIsDeleted()).map(UserMapper::toUserDto).toList();
	}

	private static @NonNull String getUserNotFoundMessage() {
		return "User not found with id: ";
	}
}
