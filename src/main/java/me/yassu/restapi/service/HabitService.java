package me.yassu.restapi.service;

import me.yassu.restapi.api.dto.*;
import me.yassu.restapi.entity.HabitEntity;
import me.yassu.restapi.exception.BadRequestException;
import me.yassu.restapi.exception.UserDeletedException;
import me.yassu.restapi.repository.HabitRepository;
import me.yassu.restapi.utility.mapper.HabitMapper;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class HabitService {

	private static final Logger logger = LoggerFactory.getLogger(HabitService.class);

	private final HabitRepository habitRepository;
	private final HabitTrackerService habitTrackerService;

	public HabitService(HabitRepository habitRepository, HabitTrackerService habitTrackerService) {
		this.habitRepository = habitRepository;
		this.habitTrackerService = habitTrackerService;
	}

	@Transactional
	public HabitDto createHabit(CreateHabitRequestDto dto) {
		logger.info("create habit start");
		HabitEntity habit = habitRepository.saveAndFlush(HabitMapper.toEntity(dto));
		habitTrackerService.createHabitTracker(new CreateHabitTrackerRequestDto(habit.getHabitId(), dto.createdBy()));
		logger.info("create habit end");
		return HabitMapper.toDto(habit);
	}

	@Transactional
	public HabitDto updateHabit(UpdateHabitRequestDto dto) {
		HabitEntity habitEntity = habitRepository.findById(dto.habitId())
				.orElseThrow(() -> new BadRequestException(getHabitNotFoundExceptionMessage() + dto.habitId()));

		if (habitEntity.isDeleted()) {
			throw new UserDeletedException("Habit is already deleted. Cannot modify habit");
		}

		if (dto.habitName() != null && !dto.habitName().isEmpty()) {
			habitEntity.setHabitName(dto.habitName());
		}
		if (dto.habitDescription() != null && !dto.habitDescription().isEmpty()) {
			habitEntity.setHabitDescription(dto.habitDescription());
		}

		habitEntity.setUpdatedAt(LocalDateTime.now(ZoneOffset.UTC));
		habitEntity.setUpdatedBy(dto.updatedBy());
		HabitEntity updatedHabit = habitRepository.save(habitEntity);
		return HabitMapper.toDto(updatedHabit);
	}

	@Transactional
	public void deleteHabit(DeleteHabitRequestDto dto) {
		HabitEntity habitEntity = habitRepository.findById(dto.habitId())
				.orElseThrow(() -> new BadRequestException(getHabitNotFoundExceptionMessage() + dto.habitId()));

		if (habitEntity.isDeleted()) {
			throw new UserDeletedException("Habit is already deleted.");
		}

		habitEntity.setDeleted(true);
		habitEntity.setDeletedAt(LocalDateTime.now(ZoneOffset.UTC));
		habitEntity.setDeletedBy(dto.updatedBy());
		habitRepository.save(habitEntity);
	}

	@Transactional(readOnly = true)
	public HabitDto getHabitById(Long habitId) {
		HabitEntity habitEntity = habitRepository.findById(habitId)
				.orElseThrow(() -> new BadRequestException(getHabitNotFoundExceptionMessage() + habitId));

		if (habitEntity.isDeleted()) {
			throw new UserDeletedException("Habit is already deleted. Please verify");
		}

		return HabitMapper.toDto(habitEntity);
	}

	@Transactional(readOnly = true)
	public List<HabitDto> getAllHabitsByUserId(Long userId) {
		return habitRepository.findByUserId(userId).stream().map(HabitMapper::toDto).toList();
	}

	private static @NonNull String getHabitNotFoundExceptionMessage() {
		return "Habit not found with id: ";
	}
}
