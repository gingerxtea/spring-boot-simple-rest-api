package me.yassu.restapi.service;

import me.yassu.restapi.api.dto.CreateHabitTrackerRequestDto;
import me.yassu.restapi.api.dto.HabitTrackerDto;
import me.yassu.restapi.api.dto.UpdateHabitTrackerRequestDto;
import me.yassu.restapi.entity.HabitTrackerEntity;
import me.yassu.restapi.exception.UserNotFoundException;
import me.yassu.restapi.repository.HabitTrackerRepository;
import me.yassu.restapi.utility.mapper.HabitTrackerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class HabitTrackerService {

	private static final Logger logger = LoggerFactory.getLogger(HabitTrackerService.class);

	private final DateTimeFormatter formatter =
			DateTimeFormatter.ofPattern("dd-MM-yyyy");

	private final HabitTrackerRepository habitTrackerRepository;

	public HabitTrackerService(HabitTrackerRepository habitTrackerRepository) {
		this.habitTrackerRepository = habitTrackerRepository;
	}

	@Transactional
	public void createHabitTracker(CreateHabitTrackerRequestDto dto) {
		logger.info("create habit tracker start");
		List<HabitTrackerEntity> entities = createHabitTrackerEntities(dto);
		habitTrackerRepository.saveAll(entities);
		logger.info("create habit tracker end");
	}

	@Transactional
	public HabitTrackerDto updateHabitTracker(UpdateHabitTrackerRequestDto dto) {
		HabitTrackerEntity entity = habitTrackerRepository.findById(dto.habitTrackerId())
				.orElseThrow(() -> new UserNotFoundException("HabitTracker not found with id: " + dto.habitTrackerId()));

		entity.setHabitCompleted(dto.isHabitCompleted());
		entity.setUpdatedAt(LocalDateTime.now(ZoneOffset.UTC));
		entity.setUpdatedBy(dto.updatedBy());
		HabitTrackerEntity updatedEntity = habitTrackerRepository.save(entity);
		return HabitTrackerMapper.toDto(updatedEntity);
	}

	@Transactional(readOnly = true)
	public List<HabitTrackerDto> getHabitTrackers(Long userId, Long habitId) {
		return habitTrackerRepository.findAllByUserIdAndHabitId(userId, habitId).stream().map(HabitTrackerMapper::toDto).toList();
	}

	private List<HabitTrackerEntity> createHabitTrackerEntities(CreateHabitTrackerRequestDto dto) {
		List<HabitTrackerEntity> entities = new ArrayList<>();
		LocalDate now = LocalDate.now(ZoneOffset.UTC);
		int year = now.getYear();
		int month = now.getMonthValue();
		for (int day = 1; day <= now.lengthOfMonth(); day++) {
			HabitTrackerEntity entity = new HabitTrackerEntity();
			entity.setHabitId(dto.habitId());
			entity.setUserId(Long.valueOf(dto.createdBy()));
			entity.setHabitDate(LocalDate.of(year, month, day).format(formatter));
			entity.setHabitCompleted(false);
			entity.setCreatedBy(dto.createdBy());
			entity.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
			entity.setUpdatedBy(dto.createdBy());
			entity.setUpdatedAt(LocalDateTime.now(ZoneOffset.UTC));
			entities.add(entity);
		}
		return entities;
	}
}
