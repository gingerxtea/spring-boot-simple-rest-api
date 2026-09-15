package me.yassu.restapi.repository;

import me.yassu.restapi.entity.HabitTrackerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitTrackerRepository extends JpaRepository<HabitTrackerEntity, Long> {

	@Query("select ht from HabitTrackerEntity ht where ht.userId=:userId and ht.habitId=:habitId")
	List<HabitTrackerEntity> findAllByUserIdAndHabitId(@Param("userId") Long userId, @Param("habitId") Long habitId);
}
