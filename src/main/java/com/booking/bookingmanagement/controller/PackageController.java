package com.booking.bookingmanagement.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.booking.bookingmanagement.configuration.ProfileConfiguration;
import com.booking.bookingmanagement.model.Packages;
import com.booking.bookingmanagement.service.PackageService;

@Controller
public class PackageController {

	@Autowired
	private PackageService packageService;
		
	@Autowired
    ServletContext context;
	
	@Autowired
	private ProfileConfiguration profileConfiguration;
	
	@RequestMapping(value = "/admin/addPackage", method = RequestMethod.GET)
	public String addPackage(@ModelAttribute("addPackage") Packages packages, Model model, HttpSession session, BindingResult result, HttpServletRequest request) throws MalformedURLException {
		//System.out.println(commonUtility.getURLBase(request.getRequestURL().toString()));
		model.addAttribute("base_url", profileConfiguration.getBaseUrl());
		return "admin/addPackage";
	}
	
	
	
	@RequestMapping(value = "/admin/addPackage", method = RequestMethod.POST)
	public void addPackage(@Valid @ModelAttribute("addPackage") Packages packages, MultipartFile file, Model model, BindingResult result, HttpServletResponse response, HttpServletRequest request) throws IOException {
		/*if (!file.getOriginalFilename().equals("")) {
			Path filePath = Paths.get(context.getRealPath("/")+File.separator+"uploads"+File.separator+file.getOriginalFilename());
        	Files.write(filePath, file.getBytes());
		}*/
		/*byte[] bytes = packages.getImage();
		if (bytes.length != 0) {
            try {
            	System.out.println(context.getRealPath("/"));
            	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            	Path filePath = Paths.get(context.getRealPath("/")+File.separator+"uploads"+File.separator+timestamp.getTime());
            	Files.write(filePath, bytes);
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + "sample.png");
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close(); 
		
                
            }catch(Exception e) {
            	
            }
		}*/
		if(result.hasErrors()) {
			response.sendRedirect("/admin/addPackage");
		}
		model.addAttribute("base_url", profileConfiguration.getBaseUrl());
		packageService.save(packages);
		
		response.sendRedirect("/admin/listPackages");
	}
	
	@RequestMapping(value = "/admin/deletePackage/{id}", method = RequestMethod.GET)
	public void deletePackage(@PathVariable("id") Long id, Model model, HttpSession session, HttpServletResponse response) throws IOException {
		packageService.delete(id);
		
		response.sendRedirect("/admin/listPackages");
	}
	
	@RequestMapping(value = "/admin/listPackages", method = RequestMethod.GET)
	public String listPackages(Model model, HttpSession session) {
		List<Packages> packageList = packageService.findAllPackages();
		model.addAttribute("packageList", packageList);
		model.addAttribute("base_url", profileConfiguration.getBaseUrl());
		return "/admin/listPackages";
	}
	
	@RequestMapping(value = "/admin/editPackage/{id}", method = RequestMethod.GET)
	public String editPackage(@PathVariable("id") Long id, @ModelAttribute("editPackage") Optional<Packages> packages, Model model, HttpSession session) {
		packages = packageService.findPackage(id);
		System.out.println(packages);
		model.addAttribute("editPackage", packages);
		model.addAttribute("base_url", profileConfiguration.getBaseUrl());
		return "/admin/editPackage";
	}
	
	@RequestMapping(value = "/admin/editPackage", method = RequestMethod.POST)
	public String editPackage(@RequestParam("id") Long id, @ModelAttribute("editPackage") Packages packages, Model model, HttpSession session, BindingResult result, HttpServletResponse response) throws IOException {
		model.addAttribute("base_url", profileConfiguration.getBaseUrl());
		if(result.hasErrors()) {
			System.out.println(result.getFieldError());
			response.sendRedirect("/admin/editPackage/"+id);
		}
		packageService.save(packages);
		
		response.sendRedirect("/admin/listPackages");
		return "editPackage";
	}
	
	
}
