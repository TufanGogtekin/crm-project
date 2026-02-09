package com.tufangogtekin.controller;

import java.util.List;

import com.tufangogtekin.dto.DtoOrder;

public interface IOrderController {

	DtoOrder saveOrder(DtoOrder dtoOrder);

    List<DtoOrder> getAllOrders();
	
}
