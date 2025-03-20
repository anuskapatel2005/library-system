package com.anushka.library_system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.anushka.library_system.entities.BookIssue;
import com.anushka.library_system.services.BookIssueService;

@RestController
@RequestMapping("/api/book-issues")
public class BookIssueController {
    @Autowired
    private BookIssueService bookIssueService;

    @PostMapping("/issue/{bookId}/{userId}")
    public ResponseEntity<?> issueBook(@PathVariable (name = "bookId") Long bookId, @PathVariable (name = "userId") Long userId) {
        try {
            BookIssue bookIssue = bookIssueService.issueBook(bookId, userId);
            return ResponseEntity.ok(bookIssue); // 200 OK
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // 400 Bad Request
        }
    }
    
    @PutMapping("/return/{issueId}")
    public ResponseEntity<?> returnBook(@PathVariable (name = "issueId")  Long issueId) {
        try {
            BookIssue bookIssue = bookIssueService.returnBook(issueId);
            return ResponseEntity.ok(bookIssue); // 200 OK
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // 400 Bad Request
        }
    }
}
