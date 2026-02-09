package com.tufangogtekin.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tufangogtekin.controller.IActivityController;
import com.tufangogtekin.dto.DtoActivity;
import com.tufangogtekin.service.IActivityService;

@RestController
@RequestMapping("/rest/api/activity")
@CrossOrigin(origins = "http://localhost:4200")
public class ActivityControllerImpl implements IActivityController {

	@Autowired
	private IActivityService activityService;
	
	@Override
	@PostMapping("/save")
	public DtoActivity saveActivity(@RequestBody DtoActivity dtoActivity) {
		return activityService.saveActivity(dtoActivity);
	}

	@Override
	@GetMapping("/list")
	public List<DtoActivity> getAllActivities() {
		return activityService.getAllActivities();
	}

}
