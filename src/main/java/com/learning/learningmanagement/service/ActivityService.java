package com.learning.learningmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;

import com.learning.learningmanagement.model.Activity;

public interface ActivityService {
	Activity save(Activity activity);

	List<Activity> findAllActivity();
	
	void delete(Long id);
	
	Optional<Activity> findActivity(Long id);

	 Page<Activity> findAll(org.springframework.data.domain.Pageable pageable);
}
