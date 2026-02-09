package com.tufangogtekin.service;

import java.util.List;

import com.tufangogtekin.dto.DtoContract;

public interface IContractService {

	DtoContract convertOfferToContract(Long offerId);
	
	public List<DtoContract> listAllContracts();
	
}
