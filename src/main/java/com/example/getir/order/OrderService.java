package com.example.getir.order;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.getir.model.dto.OrderDto;
import com.example.getir.model.entity.Order;

@Service
public class OrderService {
	
	@Autowired
	OrderDao orderDao;
	
	public List<Order> getAllOrders() {
		return orderDao.getAllOrders();
	}

	public void giveOrder(List<OrderDto> orderDtoList,Integer customerId) {
		orderDao.giveOrder(orderDtoList,customerId);
	}

	public Order getOrderById(int id) {
		return orderDao.getOrderById(id);
	}

	public List<Order> getOrderByDateInterval(Date startDate, Date endDate) {
		return orderDao.getOrderByDateInterval(startDate,endDate);
	}

}
