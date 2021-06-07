package com.example.library.matchers;

import org.mockito.ArgumentMatcher;

import com.example.library.model.Book;
import com.example.library.model.Librarian;

public class BookMatcher implements ArgumentMatcher<Book> {

	private Book left;

	public BookMatcher(Book book) {
		left = book;
	}

	@Override
	public boolean matches(Book right) {

		return left.getAuthor_name().equals(right.getAuthor_name()) && left.getId().compareTo(right.getId()) == 0
				&& left.getName().equals(right.getName()) && left.getISBN().equals(right.getISBN())
				&& left.getLibrarian().getId().compareTo(right.getLibrarian().getId()) == 0;
	}
}
