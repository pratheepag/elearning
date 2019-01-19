package com.booking.bookingmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.bookingmanagement.model.Packages;
import com.booking.bookingmanagement.service.PackageService;

@RestController
public class PackageApiController {
	@Autowired
	private PackageService packageService;
		
    @RequestMapping("/packagesList")
    public List<Packages> findAllPackages() {
        return packageService.findAllPackages();
    }
       
    @RequestMapping("/packages/{id}")
    public Optional<Packages> findById(@PathVariable("id") Long id) {
        return packageService.findPackage(id);
    }
}
