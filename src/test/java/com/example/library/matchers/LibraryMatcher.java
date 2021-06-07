package com.example.library.matchers;

import org.mockito.ArgumentMatcher;

import com.example.library.model.Librarian;

public class LibraryMatcher implements ArgumentMatcher<Librarian>{

	private Librarian left;

	public LibraryMatcher(Librarian user) {
		left = user;
	}

	@Override
	public boolean matches(Librarian right) {
		
		return left.getEmail().equals(right.getEmail()) && left.getName().equals(right.getName())
				&& left.getPassword().equals(right.getPassword()) && left.getDOB().compareTo(right.getDOB()) > 0
				&& left.getId().compareTo(right.getId()) == 0;
	}
}
