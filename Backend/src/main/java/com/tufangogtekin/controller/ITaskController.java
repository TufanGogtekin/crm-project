package com.tufangogtekin.controller;

import java.util.List;

import com.tufangogtekin.dto.DtoTask;

public interface ITaskController {

	public DtoTask saveTask(DtoTask dtoTask);
	
	List<DtoTask> getAllTask();
}
