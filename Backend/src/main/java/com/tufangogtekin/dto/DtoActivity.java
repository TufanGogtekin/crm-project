package com.tufangogtekin.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import com.tufangogtekin.enums.ActivityStatus;
import com.tufangogtekin.enums.ActivityTypes;
import lombok.Data;

@Data
public class DtoActivity {
    
    private Long id;
    private String subject;       
    private String description;    
    private LocalDate date;       
    private LocalTime hour;       
    private Integer duration;     
    private ActivityTypes type;    
    private ActivityStatus status;
    private Long customerId;  
    private Long employeeId;
}