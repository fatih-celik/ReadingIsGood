package com.example.getir.customer;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.getir.model.entity.Customer;
import com.example.getir.model.entity.Order;


@Repository
@Transactional
@SuppressWarnings("unchecked")
public class CustomerDao {	
	
	@PersistenceContext
	EntityManager entityManager;
	
	public List<Customer> getAllCustomers() {
		Query query = entityManager.createQuery("select C FROM Customer C");
		List<Customer> results = query.getResultList();
		return results;	
	}

	public void registerCustomer(Customer customer) {
		entityManager.merge(customer);		
	}

	public List<Order> getAllOrdersOfCustomer(int id) {		
		Query query = entityManager.createQuery("select O FROM Order O WHERE O.customerId = :id");
		query.setParameter("id",id );
		List<Order> results = query.getResultList();
		return results;
	}

}
