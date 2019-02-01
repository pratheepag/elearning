package com.learning.learningmanagement.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.learning.learningmanagement.configuration.ProfileConfiguration;
import com.learning.learningmanagement.model.Activity;
import com.learning.learningmanagement.service.ActivityService;

@Controller
public class ActivityController {

	@Autowired
	private ActivityService activityService;
		
	@Autowired
    ServletContext context;
	
	@Autowired
	private ProfileConfiguration profileConfiguration;
	
	@RequestMapping(value = "/admin/addActivity", method = RequestMethod.GET)
	public String addActivity(@ModelAttribute("activity") Activity activity, Model model, HttpSession session, BindingResult result, HttpServletRequest request) throws MalformedURLException {
		//System.out.println(commonUtility.getURLBase(request.getRequestURL().toString()));
		model.addAttribute("base_url", profileConfiguration.getBaseUrl());
		return "admin/addActivity";
	}
	
	
	@RequestMapping(value = "/admin/addActivity", method = RequestMethod.POST)
	//public void addPackage(@Valid @ModelAttribute("addPackage") Packages packages, MultipartFile file, Model model, BindingResult result, HttpServletResponse response, HttpServletRequest request) throws IOException {
	public String addActivity( @RequestParam("file") MultipartFile file,@Valid @ModelAttribute("activity") Activity activity, BindingResult result, Model model, HttpServletResponse response, HttpServletRequest request) throws IOException {
		System.out.println(result.hasErrors());
		result.getAllErrors().forEach(System.out::println);
		model.addAttribute("base_url", profileConfiguration.getBaseUrl());
		if(result.hasErrors()) {
			return "admin/addActivity";
		}else {
			if (!file.getOriginalFilename().equals("")) {
				Path filePath = Paths.get(context.getRealPath("/")+File.separator+"uploads"+File.separator+file.getOriginalFilename());
	        	Files.write(filePath, file.getBytes());
	        	activity.setFile_name(file.getOriginalFilename());
				activity.setFile_type(file.getContentType());
				activity.setUploaded_time(new java.util.Date());
			}
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    String name = auth.getName(); 
			activity.setOwner(name);
			model.addAttribute("base_url", profileConfiguration.getBaseUrl());
			activityService.save(activity);
			response.sendRedirect("/admin/listActivity");
		}
		return null;
	}
	
	@RequestMapping(value = "/admin/listActivity", method = RequestMethod.GET)
	public  String listPackages(@PageableDefault(size = 1, sort = "id") Pageable pageable, Model model, HttpSession session) {
		Page<Activity> page =  activityService.findAll(pageable);
		model.addAttribute("page", page);
		model.addAttribute("base_url", profileConfiguration.getBaseUrl());
		return "/admin/listActivity";
	}
	
	@RequestMapping(value = "/admin/deleteActivity/{id}", method = RequestMethod.GET)
	public void deletePackage(@PathVariable("id") Long id, Model model, HttpSession session, HttpServletResponse response) throws IOException {
		activityService.delete(id);
		
		response.sendRedirect("/admin/listActivity");
	}
	
	
	
	@RequestMapping(value = "/admin/editActivity/{id}", method = RequestMethod.GET)
	public String editActivity(@PathVariable("id") Long id, @ModelAttribute("activity") Optional<Activity> activity, Model model, HttpSession session) {
		activity = activityService.findActivity(id);
		model.addAttribute("activity", activity);
		model.addAttribute("base_url", profileConfiguration.getBaseUrl());
		return "/admin/editActivity";
	}
	
	@RequestMapping(value = "/admin/editActivity", method = RequestMethod.POST)
	public String editActivity(@RequestParam("id") Long id, @RequestParam("file") MultipartFile file, @ModelAttribute("activity") Activity activity, Model model, HttpSession session, BindingResult result, HttpServletResponse response) throws IOException {
		model.addAttribute("base_url", profileConfiguration.getBaseUrl());
		if(result.hasErrors()) {
			System.out.println(result.getFieldError());
			return "/admin/editActivity/"+id;
		}else {
			if (!file.getOriginalFilename().equals("")) {
				Path filePath = Paths.get(context.getRealPath("/")+File.separator+"uploads"+File.separator+file.getOriginalFilename());
	        	Files.write(filePath, file.getBytes());
	        	activity.setFile_name(file.getOriginalFilename());
				activity.setFile_type(file.getContentType());
				activity.setUploaded_time(new java.util.Date());
			}
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    String name = auth.getName(); 
			activity.setOwner(name);
			activityService.save(activity);
			model.addAttribute("base_url", profileConfiguration.getBaseUrl());
			response.sendRedirect("/admin/listActivity");
		}
		return null;
	}
		
}
