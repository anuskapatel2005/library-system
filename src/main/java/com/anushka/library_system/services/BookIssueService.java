package com.anushka.library_system.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.anushka.library_system.entities.Book;
import com.anushka.library_system.entities.BookIssue;
import com.anushka.library_system.entities.User;
import com.anushka.library_system.repositories.BookIssueRepository;
import com.anushka.library_system.repositories.BookRepository;
import com.anushka.library_system.repositories.UserRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class BookIssueService {
    @Autowired
    private BookIssueRepository bookIssueRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;
    
    private static final double LATE_FEE_PER_DAY = 5.0; // ₹5 per day late fee

    public BookIssue issueBook(Long bookId, Long userId) {
        Optional<Book> bookOpt = bookRepository.findById(bookId);
        Optional<User> userOpt = userRepository.findById(userId);

        if (bookOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
        if (userOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        Book book = bookOpt.get();
        User user = userOpt.get();

        if (!book.isAvailable()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book is already issued");
        }

        book.setAvailable(false); // Mark book as issued
        bookRepository.save(book);

        BookIssue bookIssue = new BookIssue();
        bookIssue.setBook(book);
        bookIssue.setUser(user);
        bookIssue.setIssueDate(LocalDate.now());
        bookIssue.setDueDate(LocalDate.now().plusDays(14)); // Due date after 14 days

        return bookIssueRepository.save(bookIssue);
    }
    
    public BookIssue returnBook(Long issueId) {
        Optional<BookIssue> bookIssueOpt = bookIssueRepository.findById(issueId);

        if (bookIssueOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Issue record not found");
        }

        BookIssue bookIssue = bookIssueOpt.get();

        if (bookIssue.getReturnDate() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book has already been returned");
        }

        // Set return date
        LocalDate returnDate = LocalDate.now();
        bookIssue.setReturnDate(returnDate);
        
     // Calculate late fee if applicable
        if (returnDate.isAfter(bookIssue.getDueDate())) {
            long overdueDays = ChronoUnit.DAYS.between(bookIssue.getDueDate(), returnDate);
            double lateFee = overdueDays * LATE_FEE_PER_DAY;
            bookIssue.setLateFee(lateFee);
        }

        // Make the book available again
        Book book = bookIssue.getBook();
        book.setAvailable(true);
        bookRepository.save(book);

        return bookIssueRepository.save(bookIssue);
    }

}

