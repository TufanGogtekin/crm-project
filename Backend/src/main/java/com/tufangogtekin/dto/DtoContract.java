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
public class DtoContract {

	private Long id;
	
	private String code;
	
	private LocalDate contractDate;
	
	private Long customerId;
	
	private String customerName;
	
	private Long employeeId;
	
	private String employeeName;

	private Long sourceOfferId;
	
	private BigDecimal grandTotal;
	
	private String status;
	
	private List<DtoContractItem> items = new ArrayList<>();
}