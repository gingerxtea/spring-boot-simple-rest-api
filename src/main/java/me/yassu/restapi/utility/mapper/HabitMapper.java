package me.yassu.restapi.utility.mapper;

import me.yassu.restapi.api.dto.CreateHabitRequestDto;
import me.yassu.restapi.api.dto.HabitDto;
import me.yassu.restapi.entity.HabitEntity;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public final class HabitMapper {

	public static HabitEntity toEntity(CreateHabitRequestDto dto) {
		HabitEntity entity = new HabitEntity();
		entity.setHabitName(dto.habitName());
		entity.setHabitDescription(dto.habitDescription());
		entity.setCreatedBy(dto.createdBy());
		entity.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
		entity.setUpdatedBy(dto.createdBy());
		entity.setUpdatedAt(LocalDateTime.now(ZoneOffset.UTC));
		return entity;
	}

	public static HabitDto toDto(HabitEntity habit) {
		return new HabitDto(
				habit.getHabitId(),
				habit.getHabitName(),
				habit.getHabitDescription()
		);
	}
}
