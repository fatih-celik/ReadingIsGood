package com.example.getir.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.getir.model.entity.Book;

@Service
public class BookService {

	@Autowired
	BookDao bookDao;

	public List<Book> getAllBooks() {
		return bookDao.getAllBooks();
	}

	public void addBook(Book book) {
		bookDao.addBook(book);
	}

	public void updateBookStock(Integer bookId, Integer quantity) {
		bookDao.updateBookStock(bookId,quantity);		
	}

}
