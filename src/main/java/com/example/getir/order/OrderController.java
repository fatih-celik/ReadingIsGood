package com.example.getir.order;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.getir.model.dto.OrderDto;
import com.example.getir.model.entity.Order;

@RestController
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@GetMapping("/order")
	private List<Order> getAllOrders() {
		return orderService.getAllOrders() ;
	}
	
	@PostMapping("/order")
	private void giveOrder(@RequestBody List<OrderDto> orderDtoList,@RequestParam Integer customerId) {
		orderService.giveOrder(orderDtoList,customerId);
	}
	
	@GetMapping("/order/{id}")
	private Order getOrderById(@PathVariable int id) {
		return orderService.getOrderById(id) ;
	}

	@GetMapping("/order/date-interval")
	private List<Order> getOrderByDateInterval(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date startDate, 
			@RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date endDate) {
		return orderService.getOrderByDateInterval(startDate,endDate) ;
	}
}
