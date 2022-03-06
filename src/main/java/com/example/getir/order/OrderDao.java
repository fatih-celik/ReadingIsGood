package com.example.getir.order;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.getir.model.dto.OrderDto;
import com.example.getir.model.entity.Book;
import com.example.getir.model.entity.Order;


@Repository
@Transactional
@SuppressWarnings("unchecked")
public class OrderDao {

	@PersistenceContext
	EntityManager entityManager;
	
	public List<Order> getAllOrders() {
			Query query = entityManager.createQuery("select O FROM Order O");
			List<Order> results = query.getResultList();
			return results;		
	}

	public void giveOrder(List<OrderDto> orderDtoList,Integer customerId) {		
		calculateOrderPrice(orderDtoList);
		calculateBookCountByOrder(orderDtoList);
		placeNewOrder(customerId,calculateOrderPrice(orderDtoList),calculateBookCountByOrder(orderDtoList));
		updateBookStock(orderDtoList);
	}

	private double calculateOrderPrice(List<OrderDto> orderDtoList) {
		double totalPrice=0;
		for (OrderDto orderDto : orderDtoList) {
			if (checkStockAndInputQuantity(orderDto.getBookId(), orderDto.getQuantity())) {
				Query query = entityManager.createQuery("select B FROM Book B where B.id= :bookId");
				query.setParameter("bookId", orderDto.getBookId());
				List<Book> books = query.getResultList();
				totalPrice+=orderDto.getQuantity() * (books.get(0).getPrice());
			}
		}
		return totalPrice;	
	}
	
	private int calculateBookCountByOrder(List<OrderDto> orderDtoList) {
		int bookCountByOrder=0;
		for (OrderDto orderDto : orderDtoList) {
			if (checkStockAndInputQuantity(orderDto.getBookId(), orderDto.getQuantity())) {
				bookCountByOrder+=orderDto.getQuantity();
			}
		}
		return bookCountByOrder;		
	}
	
	public void placeNewOrder(Integer customerId, double price,int bookCount) {
		Order order = new Order();
		order.setCustomerId(customerId);
		Date date = new Date();   
		order.setOrderDate(date);
		order.setStatusId("1");
		order.setPrice(price);
		order.setBookCount(bookCount);
		entityManager.merge(order);
	}
	
	public void updateBookStock(List<OrderDto> orderDtoList) {
		Query query = entityManager.createQuery("update BookStock BS set BS.quantity=BS.quantity- :quantity "
				+ "where BS.bookId=:bookId");
		orderDtoList.forEach(orderDto -> {
			if(checkStockAndInputQuantity(orderDto.getBookId(), orderDto.getQuantity())) {
			query.setParameter("quantity", orderDto.getQuantity());
			query.setParameter("bookId", orderDto.getBookId());
			query.executeUpdate();
			} else {
				System.out.println("We do not have a sufficient number of books BookID: (" +orderDto.getBookId()+ ") in our stocks.");
			}			
		});	
	}
	
	public boolean checkStockAndInputQuantity(Integer bookId,int quantity) {
		Query query = entityManager.createQuery("select BS.quantity FROM BookStock BS where BS.bookId=:bookId");
		query.setParameter("bookId",bookId);
		List<Integer> results = query.getResultList();
		if (results.get(0)>=quantity && quantity>0) {
			return true;
		} else {
			return false;
		}
	}
	
	public Order getOrderById(int id) {
		return entityManager.find(Order.class, id);
	}

	public List<Order> getOrderByDateInterval(Date startDate, Date endDate) {
		Query query = entityManager.createQuery("select O.id,O.orderDate,OS.description FROM Order O,OrderStatus OS "
				+ "WHERE O.orderDate>:startDate AND O.orderDate<:endDate AND O.statusId=OS.id");
		query.setParameter("startDate",startDate );
		query.setParameter("endDate",endDate );
		List<Order> results = query.getResultList();
		return results;	
	}
	
}
