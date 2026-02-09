package com.tufangogtekin.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tufangogtekin.controller.IVecihleController;
import com.tufangogtekin.dto.DtoVecihle;
import com.tufangogtekin.service.IVecihleService;

@RestController
@RequestMapping("/rest/api/vecihle")
@CrossOrigin(origins = "http://localhost:4200")
public class VecihleControllerImpl implements IVecihleController {
	
	@Autowired
	private IVecihleService vecihleService;

	@Override
	@PostMapping(path = "/save")
	public DtoVecihle saveVecihle(@RequestBody DtoVecihle dtoVecihle) {
		return vecihleService.saveVecihle(dtoVecihle);
	}

	@Override
	@GetMapping("/list")
	public List<DtoVecihle> getAllVecihle() {
		return vecihleService.getAllVecihle();
	}

}
