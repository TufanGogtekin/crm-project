package com.tufangogtekin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tufangogtekin.dto.DtoActivity;
import com.tufangogtekin.model.Activity;
import com.tufangogtekin.model.Customer;
import com.tufangogtekin.model.Employee;
import com.tufangogtekin.repository.ActivityRepository;
import com.tufangogtekin.repository.CustomerRepository;
import com.tufangogtekin.repository.EmployeeRepository;
import com.tufangogtekin.service.IActivityService;

@Service
public class ActivityServiceImpl implements IActivityService {

	@Autowired
	private ActivityRepository activityRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public DtoActivity saveActivity(DtoActivity dtoActivity) {
	    
	    Activity activity = new Activity();
	    BeanUtils.copyProperties(dtoActivity, activity);
	    
	    if (dtoActivity.getCustomerId() != null) {
	        Optional<Customer> customerOptional = customerRepository.findById(dtoActivity.getCustomerId());
	        if(customerOptional.isPresent()) {
	            activity.setCustomer(customerOptional.get());
	        }
	    }
	    
	    if (dtoActivity.getEmployeeId() != null) {
	        Optional<Employee> employeeOptional = employeeRepository.findById(dtoActivity.getEmployeeId());
	        if(employeeOptional.isPresent()) {
	            activity.setEmployee(employeeOptional.get());
	        }
	    }
	    
	    
	    Activity savedActivity = activityRepository.save(activity);
	    
	    DtoActivity responseDto = new DtoActivity();
	    BeanUtils.copyProperties(savedActivity, responseDto);
	    
	    if(savedActivity.getCustomer() != null) {
	        responseDto.setCustomerId(savedActivity.getCustomer().getId());
	    }
	    if(savedActivity.getEmployee() != null) {
	        responseDto.setEmployeeId(savedActivity.getEmployee().getId());
	    }
	    
	    return responseDto;
	}

	@Override
	public List<DtoActivity> getAllActivities() {
		List<Activity> activityList = activityRepository.findAll();
		
		List<DtoActivity> dtoList = new ArrayList<>();
		
		for (Activity activity : activityList) {
			DtoActivity dto = new DtoActivity();
			
			BeanUtils.copyProperties(activity, dto);
			
			if(activity.getCustomer() != null) {
				dto.setCustomerId(activity.getCustomer().getId());
			}
			
			if(activity.getEmployee() != null) {
				dto.setEmployeeId(activity.getEmployee().getId());
			}
			
			dtoList.add(dto);
		}
		
		return dtoList;
	}
	
	

}
