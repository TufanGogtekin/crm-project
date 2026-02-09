package com.tufangogtekin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoCustomer {
	
	private Long id;

	private String name;
	
	private String lastName;
	
	private Long employeeId;
	
	private String employeeName;
	
	private String employeeSurname;
}
