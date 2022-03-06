package com.example.getir.book;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.getir.model.entity.Book;


@Repository
@Transactional
@SuppressWarnings("unchecked")
public class BookDao {
	
	@PersistenceContext
	EntityManager entityManager;

	public List<Book> getAllBooks() {
		Query query = entityManager.createQuery("select B FROM Book B");
		List<Book> results = query.getResultList();
		return results;		
	}

	public void addBook(Book book) {
		entityManager.merge(book);
	}

	public void updateBookStock(Integer bookId, Integer quantity) {
		Query query = entityManager.createQuery("update BookStock BS set BS.quantity=:quantity where BS.bookId=:bookId");
		query.setParameter("quantity", quantity);
		query.setParameter("bookId", bookId);
		query.executeUpdate();		
	}

}
