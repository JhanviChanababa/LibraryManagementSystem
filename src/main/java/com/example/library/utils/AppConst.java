package com.example.library.utils;

public class AppConst {

	public static final class ResponseMessages {
		
		public static final String JSON_PATCH = "application/json-patch+json";
		public static final String USER_ADDED = "User added successfully";
		public static final String UNAUTHORIZED_USER = "User with specified Email Address does not exist";
		public static final String LOGGEDIN_USER = "User loggedin successfully";
		public static final String USER_UPDATED = "User updated successfully";
		public static final String USER_EXISTS = "User with same ID already exists";
		public static final String USER_EMAIL_EXISTS = "User with same email already exists";
		public static final String GET_USER = "User retrieved successfully";
		public static final String LIBRARIAN_ADDED = "Librarian added successfully";
		public static final String UNAUTHORIZED_LIBRARIAN = "Librarian with specified Email Address does not exist";
		public static final String LOGGEDIN_LIBRARIAN = "Librarian loggedin successfully";
		public static final String LIBRARIAN_UPDATED = "Librarian updated successfully";
		public static final String LIBRARIAN_EXISTS = "Librarian with same ID already exists";
		public static final String LIBRARIAN_EMAIL_EXISTS = "Librarian with same email already exists";
		public static final String GET_LIBRARIAN = "Librarian retrieved successfully";
		public static final String BOOK_ADDED = "Book added successfully";
		public static final String BOOK_UPDATED = "Book updated successfully";
		public static final String BOOK_NOT_EXISTS = "Book with given ISBN does not exist";
		public static final String BOOK_ISBN_EXISTS = "Book with same ISBN already exists";
		public static final String ISSUE_BOOK_ADDED = "Book issued successfully";
		public static final String ISSUE_BOOK_UPDATED = "Issued Book updated successfully";
		public static final String ISSUE_BOOK_NOT_EXISTS = "Book with given ID does not exist";
		public static final String USERID_BOOKID_NOT_EXISTS = "Either UserId or BookId does not exist";
		public static final String INTERNAL_SERVER_ERROR = "An internal server error occurred";
		
	}

}