package com.anushka.library_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.anushka.library_system.entities.Book;
import com.anushka.library_system.services.BookServices;

@RestController
@RequestMapping("/api/books")
public class BookController {
	@Autowired
	private BookServices bookService;

	@GetMapping
	public String test() {
		return "welcome to Anil";
	}

	@PostMapping("/add")
	public ResponseEntity<?> addBook(@RequestBody Book book) {
		try {
			Book savedBook = bookService.addBook(book);
			return ResponseEntity.status(201).body(savedBook);
		} catch (Exception e) {
			return ResponseEntity.status(400).body(e.getMessage());
		}
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<Book>> getBooks() {
	    List<Book> books = bookService.getAllBooks();
	    
	    if (books.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(books); // 204 No Content
	    }
	    
	    return ResponseEntity.ok(books); // 200 OK
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateBook(@PathVariable(name = "id") Long id, @RequestBody Book updatedBook) {
	    try {
	        Book book = bookService.updateBook(id, updatedBook);
	        return ResponseEntity.ok(book); // 200 OK
	    } catch (ResponseStatusException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getReason()); // 404 Not Found
	    }
	}

}
