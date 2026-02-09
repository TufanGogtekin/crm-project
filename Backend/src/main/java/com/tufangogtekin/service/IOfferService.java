package com.tufangogtekin.service;

import java.util.List;

import com.tufangogtekin.dto.DtoOffer;

public interface IOfferService {

	public DtoOffer saveOffer(DtoOffer dtoOffer);
	
	List<DtoOffer> listAllOffers();
	
}
