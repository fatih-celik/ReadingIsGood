package com.example.getir.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.getir.model.entity.Customer;
import com.example.getir.model.entity.Order;

@RestController
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/customer")
	private List<Customer> getAllCustomers() {
		return customerService.getAllCustomers() ;
	}
	
	@PostMapping("/customer")
	private void registerCustomer(@RequestBody Customer customer) {
		customerService.registerCustomer(customer);
	}
		
	@GetMapping("/customer/orders/{id}")
	private List<Order> getAllOrdersOfCustomer(@PathVariable int id) {
		return customerService.getAllOrdersOfCustomer(id) ;
	}

}
