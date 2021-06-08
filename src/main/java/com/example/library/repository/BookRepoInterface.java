package com.example.library.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.library.model.Book;

public interface BookRepoInterface extends JpaRepository<Book, UUID> {

	@Query("select book from Book book where book.name like %:searchStr% or book.author_name like %:searchString% and is_active=true")
	List<Book> getBooks(@Param("searchString") String searchString);
}
