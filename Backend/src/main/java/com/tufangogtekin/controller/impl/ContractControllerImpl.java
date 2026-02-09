package com.tufangogtekin.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tufangogtekin.dto.DtoContract;
import com.tufangogtekin.service.IContractService;

@RestController
@RequestMapping("/rest/api/contract")
@CrossOrigin(origins = "http://localhost:4200")
public class ContractControllerImpl{

    @Autowired
    IContractService contractService;
    
    @PostMapping(path = "/convert/{offerId}")
    public DtoContract convertOfferToContract(@PathVariable Long offerId) {
        return contractService.convertOfferToContract(offerId);
    }
    
    @GetMapping(path = "/list")
    public List<DtoContract> listAllContracts() {
        return contractService.listAllContracts();
    }
}