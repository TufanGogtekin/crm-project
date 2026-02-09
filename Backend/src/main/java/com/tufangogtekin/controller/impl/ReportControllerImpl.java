package com.tufangogtekin.controller.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tufangogtekin.controller.IReportController;
import com.tufangogtekin.service.IReportService;

@RestController
@RequestMapping("/rest/api/report")
@CrossOrigin(origins = "http://localhost:4200")
public class ReportControllerImpl implements IReportController {

	@Autowired
	private IReportService reportService;
	
	@Override
	@GetMapping(path = "/employee/{employeeId}")
	public BigDecimal calculateEmployeeSales(@PathVariable() Long employeeId) {
		return reportService.calculateEmployeeSales(employeeId);
	}

}
