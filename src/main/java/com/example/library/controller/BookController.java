package com.example.library.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.model.Book;
import com.example.library.model.Response;
import com.example.library.service.BookService;
import com.example.library.utils.AppConst;
import com.github.fge.jsonpatch.JsonPatch;

@RestController
public class BookController {

	@Autowired
	BookService bookService;

	private static final String PATH = "/book";
	private static final String RES_MESSAGE = "message";
	private static final String RES_BODY = "book";

	// Get all books
	@RequestMapping(value = PATH, method = RequestMethod.GET)
	public ResponseEntity<List<Book>> getBooks(@RequestParam String searchString) {
		Response<List<Book>> res = bookService.getBooks(searchString);
		return new ResponseEntity<List<Book>>(res.getObject(), res.getHttpStatus());
	}

	// Add a book
	@RequestMapping(value = PATH, method = RequestMethod.POST)
	public ResponseEntity<Map> addBook(@RequestBody Book book) {
		Response<Book> res = bookService.addBook(book);
		return new ResponseEntity<Map>(Map.of(RES_MESSAGE, res.getMessage(), RES_BODY, res.getObject()),
				res.getHttpStatus());
	}

	// Update a book
	@RequestMapping(value = PATH, method = RequestMethod.PUT)
	public ResponseEntity<Map> updateBook(@RequestBody Book book) {
		Response<Book> res = bookService.updateBook(book);
		return new ResponseEntity<Map>(Map.of(RES_MESSAGE, res.getMessage(), RES_BODY, res.getObject()),
				res.getHttpStatus());
	}

	// Delete a book
	@RequestMapping(value = PATH, method = RequestMethod.DELETE)
	public ResponseEntity<Map> deleteBook(@RequestParam UUID bookId) {
		Response<Book> res = bookService.deleteBook(bookId);
		return new ResponseEntity<Map>(Map.of(RES_MESSAGE, res.getMessage(), RES_BODY, res.getObject()),
				res.getHttpStatus());
	}

	// Update book details
	@RequestMapping(value = PATH, method = RequestMethod.PATCH, consumes = AppConst.ResponseMessages.JSON_PATCH)
	public ResponseEntity<Map> updateBook(@RequestParam UUID bookId, @RequestBody JsonPatch patch) {
		Response<Book> res = bookService.updateBookDetails(bookId, patch);
		return new ResponseEntity<Map>(Map.of(RES_MESSAGE, res.getMessage(), RES_BODY, res.getObject()),
				res.getHttpStatus());
	}
}
