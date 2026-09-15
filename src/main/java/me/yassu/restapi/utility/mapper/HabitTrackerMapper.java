package me.yassu.restapi.utility.mapper;

import me.yassu.restapi.api.dto.HabitTrackerDto;
import me.yassu.restapi.entity.HabitTrackerEntity;

public final class HabitTrackerMapper {

	public static HabitTrackerDto toDto(HabitTrackerEntity habitTrackerEntity) {
		return new HabitTrackerDto(
				habitTrackerEntity.getId(),
				String.valueOf(habitTrackerEntity.getHabitId()),
				habitTrackerEntity.getHabitDate(),
				habitTrackerEntity.isHabitCompleted()
		);
	}
}
