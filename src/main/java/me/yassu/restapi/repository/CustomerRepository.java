package me.yassu.restapi.repository;

import me.yassu.restapi.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

	@Query("select c from CustomerEntity c where c.id between :start and :end")
	List<CustomerEntity> findBetweenId(Long start, Long end);
}
