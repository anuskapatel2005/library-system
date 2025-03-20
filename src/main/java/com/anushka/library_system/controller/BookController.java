package com.anushka.library_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
