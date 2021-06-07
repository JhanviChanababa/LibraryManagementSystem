package com.example.library.matchers;

import org.mockito.ArgumentMatcher;

import com.example.library.model.IssuedBook;

public class IssuedBookMatcher implements ArgumentMatcher<IssuedBook> {

	private IssuedBook left;

	public IssuedBookMatcher(IssuedBook book) {
		left = book;
	}

	@Override
	public boolean matches(IssuedBook right) {

		return left.getId().compareTo(right.getId()) == 0
				&& left.getBook().getId().compareTo(right.getBook().getId()) == 0
				&& left.getUser().getId().compareTo(right.getUser().getId()) == 0
				&& left.getActual_return_date().equals(right.getActual_return_date())
				&& left.getExpected_return_date().equals(right.getExpected_return_date())
				&& left.getIssued_date().equals(right.getIssued_date());
	}
}
