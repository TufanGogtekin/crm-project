package com.tufangogtekin.service;

import java.util.List;
import com.tufangogtekin.dto.DtoOrder;

public interface IOrderService {
    
    DtoOrder saveOrder(DtoOrder dtoOrder);

    List<DtoOrder> getAllOrders();
}