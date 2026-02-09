package com.tufangogtekin.dto;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.tufangogtekin.enums.OfferStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoOffer {
	
	private Long id;
	
	private String description;
	
	private String code;
	
	private String customerName;
	
	private String employeeName;
	
	private OfferStatus status;
	
	private LocalDate createDate;
	
	private BigDecimal totalAmount;
	
	private Long employeeId;
	
	private Long customerId;
	
	private List<DtoOfferItem> items = new ArrayList<>();
	
	
}
