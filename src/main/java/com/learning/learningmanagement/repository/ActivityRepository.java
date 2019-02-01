package com.booking.bookingmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.bookingmanagement.model.Packages;

@Repository("packageRepository")
public interface PackageRepository extends JpaRepository<Packages, Long> {

}
