package com.tufangogtekin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tufangogtekin.dto.DtoTask;
import com.tufangogtekin.model.Customer;
import com.tufangogtekin.model.Employee;
import com.tufangogtekin.model.Task;
import com.tufangogtekin.repository.CustomerRepository;
import com.tufangogtekin.repository.EmployeeRepository;
import com.tufangogtekin.repository.TaskRepository;
import com.tufangogtekin.service.ITaskService;

@Service
public class TaskServiceImpl implements ITaskService {


	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private EmployeeRepository employeeRepository;


	public DtoTask saveTask(DtoTask dtoTask) {

		Task task = new Task();

		BeanUtils.copyProperties(dtoTask, task);

		if (dtoTask.getCustomerId() != null) {
			
			Optional<Customer> customerOptional = customerRepository.findById(dtoTask.getCustomerId());
			
			if (customerOptional.isPresent()) {
				
				task.setCustomer(customerOptional.get());
				
			}
			
		}

		if (dtoTask.getEmployeeId() != null) {
			
			Optional<Employee> employeeOptional = employeeRepository.findById(dtoTask.getEmployeeId());
			
			if (employeeOptional.isPresent()) {
				
				task.setEmployee(employeeOptional.get());
				
			}
			
		}

		taskRepository.save(task);

		return dtoTask;
	}

	@Override
	public List<DtoTask> getAllTask() {
	
	List<Task> taskList	= taskRepository.findAll();
	
	List<DtoTask> dtoTasks = new ArrayList<>();
	
	for (Task task : taskList) {
		
		DtoTask dtoTask = new DtoTask();
		
		BeanUtils.copyProperties(task, dtoTask);
		
		if(dtoTask.getCustomerId()==null && task.getCustomer() != null) {
			
			dtoTask.setCustomerId(task.getCustomer().getId());
			
			dtoTask.setCustomerInfo(task.getCustomer().getName() + " " + task.getCustomer().getLastName());
			
		}
		if(dtoTask.getEmployeeId()==null && task.getEmployee() != null) {
			
			dtoTask.setEmployeeId(task.getEmployee().getId());
			
			dtoTask.setEmployeeInfo(task.getEmployee().getName() + " " + task.getEmployee().getLastName());
			
		}
		
		dtoTasks.add(dtoTask);
	}
	
	return dtoTasks;
	
	}

}
