package me.yassu.restapi.api.conroller;

import jakarta.validation.Valid;
import me.yassu.restapi.api.dto.CreateHabitRequestDto;
import me.yassu.restapi.api.dto.DeleteHabitRequestDto;
import me.yassu.restapi.api.dto.HabitDto;
import me.yassu.restapi.api.dto.UpdateHabitRequestDto;
import me.yassu.restapi.service.HabitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habits")
public class HabitController {

	private final HabitService habitService;

	public HabitController(HabitService habitService) {
		this.habitService = habitService;
	}

	@PostMapping
	ResponseEntity<HabitDto> createHabit(@RequestBody @Valid CreateHabitRequestDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(habitService.createHabit(dto));
	}

	@PutMapping
	ResponseEntity<HabitDto> updateHabit(@RequestBody @Valid UpdateHabitRequestDto dto) {
		return ResponseEntity.ok(habitService.updateHabit(dto));
	}

	@DeleteMapping
	ResponseEntity<Void>  deleteHabit(@RequestBody @Valid DeleteHabitRequestDto dto) {
		habitService.deleteHabit(dto);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{habitId}")
	ResponseEntity<HabitDto> getHabit(@PathVariable("habitId") long habitId) {
		return ResponseEntity.ok(habitService.getHabitById(habitId));
	}

	@GetMapping
	ResponseEntity<List<HabitDto>> getAllHabitsByUserId(@RequestParam("userId") long userId) {
		return ResponseEntity.ok(habitService.getAllHabitsByUserId(userId));
	}
}
