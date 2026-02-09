package com.tufangogtekin.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tufangogtekin.controller.IOrderController;
import com.tufangogtekin.dto.DtoOrder;
import com.tufangogtekin.service.IOrderService;

@RestController
@RequestMapping("/rest/api/order")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderControllerImpl implements IOrderController {

    @Autowired
    private IOrderService orderService;
    
    @Override
    @PostMapping(path = "/save")
    public DtoOrder saveOrder(@RequestBody DtoOrder dtoOrder) {
        return orderService.saveOrder(dtoOrder);
    }
     
    @Override
    @GetMapping(path = "/list")
    public List<DtoOrder> getAllOrders() {
        return orderService.getAllOrders();
    }
}