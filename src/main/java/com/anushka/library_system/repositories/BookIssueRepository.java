package com.anushka.library_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anushka.library_system.entities.BookIssue;

@Repository
public interface BookIssueRepository extends JpaRepository<BookIssue, Long> {

}
