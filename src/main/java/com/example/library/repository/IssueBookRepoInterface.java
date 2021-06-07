package com.example.library.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.library.model.IssuedBook;

public interface IssueBookRepoInterface extends JpaRepository<IssuedBook, UUID> {

	@Query("select b from IssuedBook b")
	List<IssuedBook> getIssuedBooks();
}
