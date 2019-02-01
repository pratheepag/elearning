package com.learning.learningmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Service;

import com.learning.learningmanagement.model.Activity;
import com.learning.learningmanagement.repository.ActivityRepository;

@Service("activityService")
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityRepository activityRepository;
	
	public Activity save(Activity activity) {
		// TODO Auto-generated method stub
		activityRepository.saveAndFlush(activity);
		return null;
	}

	/*public List<Packages> findAllPackages() {
		// TODO Auto-generated method stub
		return packageRepository.findAll();
	}
*/
	public void delete(Long id) {
		// TODO Auto-generated method stub
		activityRepository.deleteById(id); 
	}

	public Optional<Activity> findActivity(Long id) {
		// TODO Auto-generated method stub
		return activityRepository.findById(id);
	}

	@Override
	public Page<Activity> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return activityRepository.findAll(pageable);
	
	}

	@Override
	public List<Activity> findAllActivity() {
		// TODO Auto-generated method stub
		return null;
	}

}
