package com.tufangogtekin.service.impl;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tufangogtekin.model.Order;
import com.tufangogtekin.repository.OrderRepository;
import com.tufangogtekin.service.IReportService;

@Service
public class ReportServiceImpl implements IReportService {
	
	@Autowired
	private OrderRepository orderRepository;
		
	@Override
	public BigDecimal calculateEmployeeSales(Long employeeId) {
		
	List<Order> orders = orderRepository.findAllByEmployeeId(employeeId);
	
	BigDecimal total = BigDecimal.ZERO;
	
	for (Order order : orders) {
		if(order.getTotalAmount()!=null) {
			total = total.add(order.getTotalAmount());
		}	
	}
		
		return total;
	}

}
