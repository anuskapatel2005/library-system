package com.anushka.library_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	 



}
