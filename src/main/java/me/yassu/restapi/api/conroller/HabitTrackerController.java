package me.yassu.restapi.api.conroller;

import jakarta.validation.Valid;
import me.yassu.restapi.api.dto.HabitTrackerDto;
import me.yassu.restapi.api.dto.UpdateHabitTrackerRequestDto;
import me.yassu.restapi.service.HabitTrackerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habit-tracker")
public class HabitTrackerController {

	private final HabitTrackerService habitTrackerService;

	public HabitTrackerController(HabitTrackerService habitTrackerService) {
		this.habitTrackerService = habitTrackerService;
	}

	@PutMapping
	ResponseEntity<HabitTrackerDto> updateHabitTracker(@RequestBody @Valid UpdateHabitTrackerRequestDto dto) {
		return ResponseEntity.ok(habitTrackerService.updateHabitTracker(dto));
	}

	@GetMapping
	ResponseEntity<List<HabitTrackerDto>> getAllHabitTrackers(@RequestParam("userId") Long userId, @RequestParam("habitId") Long habitId) {
		return ResponseEntity.ok(habitTrackerService.getHabitTrackers(userId, habitId));
	}
}
