package com.tufangogtekin.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tufangogtekin.enums.TaskStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoTask {

	private String title;
	
	private String description;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dueDate;
	
	@Enumerated(EnumType.STRING)
	private TaskStatus status;
	
	private Long customerId;
	
	private String customerInfo;
	
	private String employeeInfo;
	
	private Long employeeId;
	
}
