package com.tufangogtekin.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tufangogtekin.controller.IOfferController;
import com.tufangogtekin.dto.DtoOffer;
import com.tufangogtekin.service.IOfferService;

@RestController
@RequestMapping("/rest/api/offer")
@CrossOrigin(origins = "http://localhost:4200")
public class OfferControllerImpl implements IOfferController {

	@Autowired
	private IOfferService offerService;
	
	@Override
	@PostMapping(path = "/save")
	public DtoOffer saveOffer(@RequestBody DtoOffer dtoOffer) {
		return offerService.saveOffer(dtoOffer);
	}
	
	@Override
	@GetMapping(path = "/list")
	public List<DtoOffer> listAllOffers() {
		return offerService.listAllOffers();
	}
}