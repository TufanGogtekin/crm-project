package com.tufangogtekin.service;

import java.util.List;

import com.tufangogtekin.dto.DtoVecihle;

public interface IVecihleService {

	public DtoVecihle saveVecihle(DtoVecihle dtoVecihle);
	
	public List<DtoVecihle> getAllVecihle();
	
}
