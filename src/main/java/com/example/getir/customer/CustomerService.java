package com.example.getir.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.getir.model.entity.Customer;
import com.example.getir.model.entity.Order;

@Service
public class CustomerService {
	
	@Autowired
	CustomerDao customerDao;
	
	public List<Customer> getAllCustomers() {
		return customerDao.getAllCustomers();
	}
	
	public void registerCustomer(Customer customer) {
		customerDao.registerCustomer(customer);
	}

	public List<Order> getAllOrdersOfCustomer(int id) {
		return customerDao.getAllOrdersOfCustomer(id);
	}
	
}
