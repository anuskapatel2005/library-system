package com.anushka.library_system.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.anushka.library_system.entities.Book;
import com.anushka.library_system.repositories.BookRepository;

@Service
public class BookServices {
	 @Autowired 
	 private BookRepository bookRepository;
	 
	 public Book addBook(Book book) {
	        if (bookRepository.existsByIsbn(book.getIsbn())) {
	            throw new ResponseStatusException(HttpStatus.CONFLICT, "Book with this ISBN already exists");
	        }
	        return bookRepository.save(book);
	    }

}
