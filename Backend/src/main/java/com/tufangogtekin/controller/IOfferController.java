package com.tufangogtekin.controller;

import java.util.List;

import com.tufangogtekin.dto.DtoOffer;

public interface IOfferController {

	public DtoOffer saveOffer(DtoOffer dtoOffer);
	
	public List<DtoOffer> listAllOffers();
	
}
