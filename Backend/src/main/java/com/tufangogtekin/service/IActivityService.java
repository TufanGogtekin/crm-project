package com.tufangogtekin.service;

import java.util.List;

import com.tufangogtekin.dto.DtoActivity;

public interface IActivityService {
  
	public DtoActivity saveActivity(DtoActivity dtoActivity);
	
	public List<DtoActivity> getAllActivities(); 
}
