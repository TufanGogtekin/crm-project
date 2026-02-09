package com.tufangogtekin.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoOrder {

	private Long id;
	
	private String code;  
	
    private LocalDate orderDate;
	
	private String status;   
	
	private BigDecimal totalAmount; 
	
	private Long customerId;
	
	private String customerName;

	private Long employeeId;
	
	private String employeeName;
	
	private List<DtoOrderItem> items = new ArrayList<>();
	
}