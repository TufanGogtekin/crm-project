package com.tufangogtekin.controller;

import java.util.List;

import com.tufangogtekin.dto.DtoVecihle;

public interface IVecihleController {

	public DtoVecihle saveVecihle(DtoVecihle dtoVecihle);
	
	public List<DtoVecihle> getAllVecihle();
	
}
