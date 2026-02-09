package com.tufangogtekin.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tufangogtekin.controller.ITaskController;
import com.tufangogtekin.dto.DtoTask;
import com.tufangogtekin.service.ITaskService;

@RestController
@RequestMapping("/rest/api/task")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskControllerImpl implements ITaskController{
	
	@Autowired
	private ITaskService taskService;

	@Override
	@PostMapping(path = "/save")
	public DtoTask saveTask(@RequestBody DtoTask dtoTask) {
		
		return taskService.saveTask(dtoTask);
	}

	@Override
	@GetMapping(path = "/list")
	public List<DtoTask> getAllTask() {	
		return taskService.getAllTask();
	}

}
