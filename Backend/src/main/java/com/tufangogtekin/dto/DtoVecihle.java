package com.tufangogtekin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoVecihle {

	private String plate;
	
	private String model;
	
	private Integer productionYear;
	
	private Long employeeId;
	
}
