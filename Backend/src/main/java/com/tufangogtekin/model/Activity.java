package com.tufangogtekin.model;

import java.time.LocalDate;
import java.time.LocalTime;

import com.tufangogtekin.enums.ActivityStatus;
import com.tufangogtekin.enums.ActivityTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "activities")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "description", length = 1000)
	private String description;
	
	@Column(name = "subject")
	private String subject;

	@Column(name = "date")
	private LocalDate date;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private ActivityStatus status;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ActivityTypes type;
	
	@Column(name = "hour")
	private LocalTime hour;
		
	@Column(name = "duration_minutes")
	private Integer duration;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;

}
