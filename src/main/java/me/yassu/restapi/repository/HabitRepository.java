package me.yassu.restapi.repository;

import me.yassu.restapi.entity.HabitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitRepository extends JpaRepository<HabitEntity, Long> {

	@Query("select h from HabitEntity h where h.createdBy=:userId and h.isDeleted=false")
	List<HabitEntity> findByUserId(@Param("userId") Long userId);
}
