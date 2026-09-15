package me.yassu.restapi.api.conroller;

import jakarta.validation.Valid;
import me.yassu.restapi.service.UserService;
import me.yassu.restapi.api.dto.CreateUserRequestDto;
import me.yassu.restapi.api.dto.DeleteUserRequestDto;
import me.yassu.restapi.api.dto.UpdateUserRequestDto;
import me.yassu.restapi.api.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	ResponseEntity<UserDto> createUser(@RequestBody @Valid CreateUserRequestDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(dto));
	}

	@PutMapping
	ResponseEntity<UserDto> updateUser(@RequestBody @Valid UpdateUserRequestDto dto) {
		return ResponseEntity.ok(userService.updateUser(dto));
	}

	@DeleteMapping
	ResponseEntity<Void> deleteUser(@RequestBody @Valid DeleteUserRequestDto dto) {
		userService.deleteUser(dto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@PathVariable(name = "userId") Long userId) {
		logger.info("get user by id: {}", userId);
		return ResponseEntity.ok(userService.getUserById(userId));
	}

	@GetMapping
	ResponseEntity<List<UserDto>> getAllUsers() {
		logger.info("get all users");
		return ResponseEntity.ok(userService.getAllUsers());
	}
}
