package com.tufangogtekin.controller;

import java.util.List;

import com.tufangogtekin.dto.DtoActivity;

public interface IActivityController {

	public DtoActivity saveActivity(DtoActivity dtoActivity);
	
	public List<DtoActivity> getAllActivities();
	
}
