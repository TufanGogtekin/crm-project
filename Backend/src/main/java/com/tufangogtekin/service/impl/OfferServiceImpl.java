package com.tufangogtekin.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tufangogtekin.dto.DtoOffer;
import com.tufangogtekin.dto.DtoOfferItem;
import com.tufangogtekin.enums.OfferStatus;
import com.tufangogtekin.model.Customer;
import com.tufangogtekin.model.Employee;
import com.tufangogtekin.model.Offer;
import com.tufangogtekin.model.OfferItem;
import com.tufangogtekin.model.Product;
import com.tufangogtekin.repository.CustomerRepository;
import com.tufangogtekin.repository.EmployeeRepository;
import com.tufangogtekin.repository.OfferRepository;
import com.tufangogtekin.repository.ProductRepository;
import com.tufangogtekin.service.IOfferService;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OfferServiceImpl implements IOfferService {
	
	@Autowired
	private OfferRepository offerRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public DtoOffer saveOffer(DtoOffer dtoOffer) {
	    
	    Offer offer = new Offer();
	    
	    BeanUtils.copyProperties(dtoOffer, offer);
	    
	    
	    if(dtoOffer.getCustomerId() != null) {
	        Optional<Customer> optionalCustomer = customerRepository.findById(dtoOffer.getCustomerId());
	        if(optionalCustomer.isPresent()) {
	            offer.setCustomer(optionalCustomer.get());
	        }
	    }
	    
	   
	    if(dtoOffer.getEmployeeId() != null) {
	        Optional<Employee> optionalEmployee = employeeRepository.findById(dtoOffer.getEmployeeId());
	        if(optionalEmployee.isPresent()) {
	            offer.setEmployee(optionalEmployee.get());
	        }
	    }
	    
	    
	    if(offer.getCreateDate() == null) {
	        offer.setCreateDate(LocalDate.now()); 
	    }
	    if(offer.getStatus() == null) {
	        offer.setStatus(OfferStatus.DRAFT); 
	    }
	    
	    List<OfferItem> offerItems = new ArrayList<>();
		BigDecimal grandTotal = BigDecimal.ZERO;
		
		for(DtoOfferItem dtoItem : dtoOffer.getItems()) {
			
			OfferItem offerItem = new OfferItem();
			
			if(dtoItem.getProductId()!=null) {
				Optional<Product> productOptional = productRepository.findById(dtoItem.getProductId());
				if(productOptional.isPresent()) {
					offerItem.setProduct(productOptional.get());
				}
			}
			
			offerItem.setPrice(dtoItem.getPrice());
			offerItem.setQuantity(dtoItem.getQuantity());
			
			BigDecimal lineTotal = offerItem.getPrice().multiply(BigDecimal.valueOf(offerItem.getQuantity()));
			offerItem.setTotalAmount(lineTotal);
			
			grandTotal = grandTotal.add(lineTotal);
			offerItem.setOffer(offer);			
			offerItems.add(offerItem);
		
		}
		
		offer.setItems(offerItems);
		offer.setTotalAmount(grandTotal);

		Offer savedOffer = offerRepository.save(offer);

		return toDto(savedOffer);
	
	}
	
	private DtoOffer toDto(Offer offer) {
		DtoOffer dto = new DtoOffer();
		BeanUtils.copyProperties(offer, dto);
		
        if (offer.getCreateDate() != null) {
            
            dto.setCreateDate(offer.getCreateDate()); 

        }
        

        if (offer.getTotalAmount() != null) {
 
            dto.setTotalAmount(offer.getTotalAmount());
            
        }

		
		dto.setCode(offer.getCode());
        dto.setStatus(offer.getStatus()); 

		if(offer.getCustomer() != null) {
			dto.setCustomerId(offer.getCustomer().getId());
			dto.setCustomerName(offer.getCustomer().getName() + " " + offer.getCustomer().getLastName());
		}
		
		if(offer.getEmployee() != null) {
			dto.setEmployeeId(offer.getEmployee().getId());
			dto.setEmployeeName(offer.getEmployee().getName() + " " + offer.getEmployee().getLastName());
		}
		
		return dto;
	}

	@Override
	public List<DtoOffer> listAllOffers() {

		List<Offer> allOffers = offerRepository.findAll();		
		
		List<DtoOffer> dtoList = new ArrayList<>();
		
		for (Offer offer : allOffers) {
			
			dtoList.add(toDto(offer));
		}
		
		return dtoList;
	} 
}
