package com.tufangogtekin.service;

import java.util.List;

import com.tufangogtekin.dto.DtoTask;

public interface ITaskService {

	public DtoTask saveTask(DtoTask dtoTask);
	
	List<DtoTask> getAllTask();
}
