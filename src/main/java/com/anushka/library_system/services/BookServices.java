package com.anushka.library_system.services;

import java.util.List;
import java.util.Optional;

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
	
	public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
	
	public Book updateBook(Long id, Book updatedBook) {
	    // Find the existing book by ID
	    Optional<Book> existingBookOpt = bookRepository.findById(id);
	    
	    if (existingBookOpt.isPresent()) {
	        Book existingBook = existingBookOpt.get();
	        
	        // Update book details
	        existingBook.setTitle(updatedBook.getTitle());
	        existingBook.setAuthor(updatedBook.getAuthor());
	        existingBook.setIsbn(updatedBook.getIsbn());
	        existingBook.setAvailable(updatedBook.isAvailable());

	        // Save and return updated book
	        return bookRepository.save(existingBook);
	    } else {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found with ID: " + id);
	    }
	}

}
