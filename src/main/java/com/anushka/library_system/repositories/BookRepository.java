package com.anushka.library_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anushka.library_system.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	boolean existsByIsbn(String isbn);
}
