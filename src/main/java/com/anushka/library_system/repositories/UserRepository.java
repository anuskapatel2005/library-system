package com.anushka.library_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anushka.library_system.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
