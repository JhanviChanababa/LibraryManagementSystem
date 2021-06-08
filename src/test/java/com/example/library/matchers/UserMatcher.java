package com.example.library.matchers;

import org.mockito.ArgumentMatcher;

import com.example.library.model.User;

public class UserMatcher implements ArgumentMatcher<User> {

	private User left;

	public UserMatcher(User user) {
		left = user;
	}

	@Override
	public boolean matches(User right) {

		return left.getEmail().equals(right.getEmail()) && left.getName().equals(right.getName())
				&& left.getPassword().equals(right.getPassword()) && left.getId().compareTo(right.getId()) == 0;
	}
}
