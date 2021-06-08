package com.example.library.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.library.model.Librarian;

public interface LibrarianRepoInterface extends JpaRepository<Librarian, UUID> {

	@Query("select librarian from Librarian librarian where librarian.email = :email and librarian.password = :password")
	Librarian authenticateUser(@Param("email") String email, @Param("password") String password);
}
