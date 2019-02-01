package com.learning.learningmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.learningmanagement.model.Activity;

@Repository("activityRepository")
public interface ActivityRepository extends JpaRepository<Activity, Long> {

	//List<Packages> findAllByOrderByIdDesc();
	//List<Packages> findAll(Pageable pageable);

}
