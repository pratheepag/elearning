package com.booking.bookingmanagement.service;

import java.util.List;
import java.util.Optional;

import com.booking.bookingmanagement.model.Packages;

public interface PackageService {
	Packages save(Packages packages);

	List<Packages> findAllPackages();
	
	void delete(Long id);
	
	Optional<Packages> findPackage(Long id);
}
