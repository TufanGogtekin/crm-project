package com.tufangogtekin.controller;

import java.util.List;

import com.tufangogtekin.dto.DtoContract;

public interface IContractController {

	DtoContract convertOfferToContract(Long offerId);
	
	public List<DtoContract> listAllContracts();
	
}
