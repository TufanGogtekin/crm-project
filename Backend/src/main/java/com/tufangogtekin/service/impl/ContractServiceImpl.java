package com.tufangogtekin.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tufangogtekin.dto.DtoContract;
import com.tufangogtekin.enums.OfferStatus; 
import com.tufangogtekin.model.Contract;
import com.tufangogtekin.model.Offer;
import com.tufangogtekin.repository.ContractRepository;
import com.tufangogtekin.repository.OfferRepository;
import com.tufangogtekin.service.IContractService;

@Service
@Transactional
public class ContractServiceImpl implements IContractService {

    @Autowired
    private ContractRepository contractRepository;
    
    @Autowired
    private OfferRepository offerRepository;

    @Override
    public DtoContract convertOfferToContract(Long offerId) {
        Optional<Offer> optionalOffer = offerRepository.findById(offerId);
        
        if (optionalOffer.isEmpty()) {
            throw new RuntimeException("Teklif bulunamadı! ID: " + offerId);
        }
        
        Offer offer = optionalOffer.get();
        
        Contract contract = new Contract();
        
        contract.setSourceOfferId(offer.getId());
        contract.setCustomer(offer.getCustomer());
        contract.setEmployee(offer.getEmployee());
        contract.setGrandTotal(offer.getTotalAmount());
        contract.setDescription(offer.getDescription());
        
        contract.setContractDate(LocalDate.now());
        contract.setCode("CNT-" + UUID.randomUUID().toString().substring(0, 8)); 
        contract.setStatus("ACTIVE"); 
        
        Contract savedContract = contractRepository.save(contract);
        
        offer.setStatus(OfferStatus.CONVERTED); 
        offerRepository.save(offer);
        
        return toDto(savedContract);
    }

    @Override
    public List<DtoContract> listAllContracts() {
        List<Contract> list = contractRepository.findAll();
        List<DtoContract> dtoList = new ArrayList<>();
        
        for (Contract c : list) {
            dtoList.add(toDto(c));
        }
        return dtoList;
    }
    

    private DtoContract toDto(Contract c) {
        DtoContract dto = new DtoContract();
        BeanUtils.copyProperties(c, dto);
        
        dto.setContractDate(c.getContractDate());
        dto.setGrandTotal(c.getGrandTotal());
        dto.setStatus(c.getStatus());
        dto.setCode(c.getCode());
        dto.setSourceOfferId(c.getSourceOfferId());
        
        if(c.getCustomer() != null) {
             dto.setCustomerId(c.getCustomer().getId());
             dto.setCustomerName(c.getCustomer().getName() + " " + c.getCustomer().getLastName());
        }
        
        if(c.getEmployee() != null) {
             dto.setEmployeeId(c.getEmployee().getId());
             dto.setEmployeeName(c.getEmployee().getName() + " " + c.getEmployee().getLastName());
        }
        
        return dto;
    }
}