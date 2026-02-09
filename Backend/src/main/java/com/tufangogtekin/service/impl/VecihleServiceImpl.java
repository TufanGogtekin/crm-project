package com.tufangogtekin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tufangogtekin.dto.DtoVecihle;
import com.tufangogtekin.model.Employee;
import com.tufangogtekin.model.Vecihle;
import com.tufangogtekin.repository.EmployeeRepository;
import com.tufangogtekin.repository.VecihleRepository;
import com.tufangogtekin.service.IVecihleService;

@Service
public class VecihleServiceImpl implements IVecihleService {

	@Autowired
	private VecihleRepository vecihleRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public DtoVecihle saveVecihle(DtoVecihle dtoVecihle) {

		Vecihle vecihle = new Vecihle();

		BeanUtils.copyProperties(dtoVecihle, vecihle);

		if (dtoVecihle.getEmployeeId() != null) {
			Optional<Employee> employeeOptional = employeeRepository.findById(dtoVecihle.getEmployeeId());
			if (employeeOptional.isPresent()) {
				vecihle.setEmployee(employeeOptional.get());
			}
		}

		Vecihle savedVecihle = vecihleRepository.save(vecihle);
		
		DtoVecihle reVecihle = new DtoVecihle();
		
		BeanUtils.copyProperties(savedVecihle, reVecihle);
		
		if(savedVecihle.getEmployee().getId()!=null) {
			reVecihle.setEmployeeId(savedVecihle.getEmployee().getId());
		}

		return reVecihle;
	}

	@Override
	public List<DtoVecihle> getAllVecihle() {
		List<Vecihle> vecihles = vecihleRepository.findAll();
		
		List<DtoVecihle> vecihleList = new ArrayList<>();
		
		for (Vecihle vecihle : vecihles) {
			DtoVecihle dtoVecihle = new DtoVecihle();
			BeanUtils.copyProperties(vecihle, dtoVecihle);
			if (vecihle.getEmployee() != null) {
	            dtoVecihle.setEmployeeId(vecihle.getEmployee().getId());
	        }
			vecihleList.add(dtoVecihle);
		}
		return vecihleList;
	}

}
