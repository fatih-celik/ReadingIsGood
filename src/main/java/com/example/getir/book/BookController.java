package com.example.getir.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.getir.model.entity.Book;

@RestController
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@GetMapping("/book")
	private List<Book> getAllBooks() {
		return bookService.getAllBooks() ;
	}
	
	@PostMapping("/book")
	private void addBook(@RequestBody Book book) {
		bookService.addBook(book);
	}

	@PutMapping("/book/stock")
	private void updateBookStock(@RequestParam Integer bookId,@RequestParam Integer quantity) {
		bookService.updateBookStock(bookId,quantity);
	}
	
}
