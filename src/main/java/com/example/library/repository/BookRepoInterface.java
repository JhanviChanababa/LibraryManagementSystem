package com.example.library.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.library.model.Book;

public interface BookRepoInterface extends JpaRepository<Book, UUID> {

	@Query("select b from Book b where b.name like %:searchStr% or b.author_name like %:searchStr% and is_active=true")
	List<Book> getBooks(@Param("searchStr") String searchStr);
}
