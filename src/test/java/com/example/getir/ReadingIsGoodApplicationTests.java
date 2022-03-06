package com.example.getir;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.getir.book.BookDao;
import com.example.getir.customer.CustomerDao;
import com.example.getir.model.dto.MonthlyStatisticsDto;
import com.example.getir.model.entity.Book;
import com.example.getir.model.entity.Customer;
import com.example.getir.model.entity.Order;
import com.example.getir.order.OrderDao;
import com.example.getir.statistics.StatisticsDao;

@SpringBootTest
class ReadingIsGoodApplicationTests {

	@Autowired
	BookDao bookDao;
	
	@Autowired
	CustomerDao customerDao;
	
	@Autowired
	OrderDao orderDao;

	@Autowired
	StatisticsDao statisticsDao;
	
	
	@Test
	public void testGetAllBooks() {
		List<Book> books =bookDao.getAllBooks();
		assertThat(books).size().isGreaterThan(0);
	}
	
	@Test
	public void testAddBook() {
		Book book =new Book();
		book.setId(20);
		book.setAuthorId(3);
		book.setPrice(15.89);
		book.setTitle("Donusum");
		book.setTypeId(1);
		bookDao.addBook(book);
	}
	
	@Test
	public void testGetAllCustomers() {
		List<Customer> customers = customerDao.getAllCustomers();
		assertThat(customers).size().isGreaterThan(0);
	}
	
	@Test 
	public void testRegisterCustomer() {
		Customer customer = new Customer();
		customer.setId(33);
		customer.setName("James W.");
		customer.setSurname("Milner");
		customer.setEmail("jamesmilner123@gmail.com");
		customer.setPassword("AA121213");
		customer.setPhone("+9045454547");
		customer.setAddress("Beykoz");
		customerDao.registerCustomer(customer);
	}
	
	@Test
	public void testGetAllOrders() {
		List<Order> orders= orderDao.getAllOrders();
		assertThat(orders).size().isGreaterThan(0);
	}

	
	@Test 
	public void testGetOrderById() {
		Order order = orderDao.getOrderById(5);
		assertEquals(93.5, order.getPrice());
	}
	
	@Test
	public void testNewOrder() {
		List<MonthlyStatisticsDto> monthlyStatisticsDtos = statisticsDao.getMonthlyStatistics(3);
		assertThat(monthlyStatisticsDtos).size().isGreaterThan(0);		
	}
}
