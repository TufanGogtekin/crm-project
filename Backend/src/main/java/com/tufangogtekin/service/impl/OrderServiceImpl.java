package com.tufangogtekin.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tufangogtekin.dto.DtoOrder;
import com.tufangogtekin.dto.DtoOrderItem;
import com.tufangogtekin.model.Customer;
import com.tufangogtekin.model.Employee;
import com.tufangogtekin.model.Order;
import com.tufangogtekin.model.OrderItem;
import com.tufangogtekin.model.Product;
import com.tufangogtekin.repository.CustomerRepository;
import com.tufangogtekin.repository.EmployeeRepository;
import com.tufangogtekin.repository.OrderRepository;
import com.tufangogtekin.repository.ProductRepository;
import com.tufangogtekin.service.IOrderService;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private ProductRepository productRepository;

    @Override
	public DtoOrder saveOrder(DtoOrder dtoOrder) {
		try {
			System.out.println("🚀 SİPARİŞ KAYDI BAŞLADI!");
			System.out.println("Gelen Müşteri ID: " + dtoOrder.getCustomerId());
			System.out.println("Gelen Ürün Sayısı: " + (dtoOrder.getItems() != null ? dtoOrder.getItems().size() : 0));
			
			Order order = new Order();
			BeanUtils.copyProperties(dtoOrder, order);
			
			if (order.getCode() == null || order.getCode().isEmpty()) {
				order.setCode("ORD-" + UUID.randomUUID().toString().substring(0, 8));
			}

			if(dtoOrder.getCustomerId() != null) {
				Optional<Customer> optional = customerRepository.findById(dtoOrder.getCustomerId());
				if (optional.isPresent()) {
					order.setCustomer(optional.get());
				} else {
					System.out.println("⚠️ UYARI: Müşteri Bulunamadı! ID: " + dtoOrder.getCustomerId());
				}
			}
			
			if(dtoOrder.getEmployeeId() != null) {
				Optional<Employee> optional = employeeRepository.findById(dtoOrder.getEmployeeId());
				optional.ifPresent(order::setEmployee);
			}
			
			if(order.getOrderDate() == null) {
				order.setOrderDate(LocalDate.now());
			}
			
			List<OrderItem> orderItems = new ArrayList<>();
			BigDecimal grandTotal = BigDecimal.ZERO;
			
			if (dtoOrder.getItems() != null && !dtoOrder.getItems().isEmpty()) {
				for (DtoOrderItem dtoItem : dtoOrder.getItems()) {
					OrderItem item = new OrderItem();
					
					if(dtoItem.getProductId() != null) {
						Optional<Product> p = productRepository.findById(dtoItem.getProductId());
						if(p.isPresent()) {
							item.setProduct(p.get());
							if (dtoItem.getUnitPrice() == null) {
								dtoItem.setUnitPrice(p.get().getPrice());
							}
						} else {
							System.out.println("❌ HATA: Ürün ID veritabanında yok! ID: " + dtoItem.getProductId());
							throw new RuntimeException("Ürün bulunamadı! ID: " + dtoItem.getProductId());
						}
					}

					item.setQuantity(dtoItem.getQuantity() != null ? dtoItem.getQuantity() : 1);
					
					item.setUnitPrice(dtoItem.getUnitPrice() != null ? dtoItem.getUnitPrice() : BigDecimal.ZERO);
					
					BigDecimal lineTotal = item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
					item.setSubTotal(lineTotal);
					grandTotal = grandTotal.add(lineTotal);
					
					item.setOrder(order);
					orderItems.add(item);
				}
			}
			
			order.setItems(orderItems);
			order.setTotalAmount(grandTotal);

			System.out.println("💾 Veritabanına Kaydediliyor...");
			Order savedOrder = orderRepository.save(order);
			System.out.println("✅ KAYIT BAŞARILI! ID: " + savedOrder.getId());
			
			return toDto(savedOrder);
			
		} catch (Exception e) {
			System.err.println("❌❌❌ SİPARİŞ KAYDEDİLİRKEN HATA OLUŞTU! ❌❌❌");
			e.printStackTrace(); 
			throw e; 
		}
	}

    @Override
    public List<DtoOrder> getAllOrders() {
        List<Order> list = orderRepository.findAll();
        List<DtoOrder> dtoList = new ArrayList<>();
        
        for (Order order : list) {
            dtoList.add(toDto(order));
        }
        return dtoList;
    }
    
    private DtoOrder toDto(Order order) {
        DtoOrder dto = new DtoOrder();
        BeanUtils.copyProperties(order, dto);
        
        dto.setOrderDate(order.getOrderDate());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setCode(order.getCode());
        
        if(order.getCustomer() != null) {
            dto.setCustomerId(order.getCustomer().getId());
            dto.setCustomerName(order.getCustomer().getName() + " " + order.getCustomer().getLastName());
        }
        
        if(order.getEmployee() != null) {
            dto.setEmployeeId(order.getEmployee().getId());
            dto.setEmployeeName(order.getEmployee().getName() + " " + order.getEmployee().getLastName());
        }
        
        List<DtoOrderItem> itemDtos = new ArrayList<>();
        if(order.getItems() != null) {
            for (OrderItem item : order.getItems()) {
                DtoOrderItem itemDto = new DtoOrderItem();
                BeanUtils.copyProperties(item, itemDto);
                
                if(item.getProduct() != null) {
                    itemDto.setProductId(item.getProduct().getId());
                    itemDto.setProductName(item.getProduct().getName());
                }
                itemDtos.add(itemDto);
            }
        }
        dto.setItems(itemDtos);
        
        return dto;
    }
}