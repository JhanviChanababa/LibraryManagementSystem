package com.example.library.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.library.model.Book;
import com.example.library.model.Response;
import com.example.library.repository.BookRepoInterface;
import com.example.library.utils.AppConst;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

@Service
public class BookService {

	@Autowired
	BookRepoInterface bookRepoInterface;

	private ObjectMapper objectMapper = new ObjectMapper();

	public Response<Book> addBook(Book book) {

		Response<Book> res = new Response<Book>();

		try {

			Book addedBook = bookRepoInterface.save(book);
			
			if (addedBook == null) {
				res.setMessage(AppConst.ResponseMessages.INTERNAL_SERVER_ERROR);
				res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				res.setObject(addedBook);
				res.setMessage(AppConst.ResponseMessages.BOOK_ADDED);
				res.setHttpStatus(HttpStatus.OK);
			}

		} catch (Exception e) {

			if (e.getCause() instanceof ConstraintViolationException) {
				res.setMessage(AppConst.ResponseMessages.BOOK_ISBN_EXISTS);
				res.setHttpStatus(HttpStatus.CONFLICT);
			} else {
				res.setMessage(AppConst.ResponseMessages.INTERNAL_SERVER_ERROR);
				res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return res;
	}

	public Response<List<Book>> getBooks(String searchString) {
		
		Response<List<Book>> res = new Response<>();
		List<Book> books = bookRepoInterface.getBooks(searchString);
		
		if(books == null) {
			res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			res.setHttpStatus(HttpStatus.OK);
		}
		
		res.setObject(books);
		return res;
	}
	
	public Response<Book> updateBook(UUID bookId, Book book) {

		Response<Book> res = new Response<Book>();

		try {

			Optional<Book> findBook = bookRepoInterface.findById(bookId);

			if (findBook.isPresent()) {

				Book updatedBook = bookRepoInterface.save(book);
				
				if (updatedBook == null) {
					res.setMessage(AppConst.ResponseMessages.INTERNAL_SERVER_ERROR);
					res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
				} else {
					res.setObject(updatedBook);
					res.setMessage(AppConst.ResponseMessages.BOOK_UPDATED);
					res.setHttpStatus(HttpStatus.OK);
				}

			} else {
				res.setMessage(AppConst.ResponseMessages.BOOK_NOT_EXISTS);
				res.setHttpStatus(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {

			if (e.getCause() instanceof ConstraintViolationException) {
				res.setMessage(AppConst.ResponseMessages.BOOK_NOT_EXISTS);
				res.setHttpStatus(HttpStatus.CONFLICT);
			} else {
				res.setMessage(AppConst.ResponseMessages.INTERNAL_SERVER_ERROR);
				res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return res;
	}

	public Response<Book> deleteBook(UUID id) {

		Response<Book> res = new Response<Book>();

		try {

			bookRepoInterface.deleteById(id);
			res.setMessage(AppConst.ResponseMessages.BOOK_UPDATED);
			res.setHttpStatus(HttpStatus.OK);

		} catch (Exception e) {
			res.setMessage(AppConst.ResponseMessages.BOOK_NOT_EXISTS);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
		}

		return res;
	}

	public Response<Book> updateBookDetails(UUID id, JsonPatch patch) {

		Response<Book> res = new Response<Book>();

		try {

			Book bookDetails = bookRepoInterface.findById(id).orElseThrow(RuntimeException::new);
			Book bookPatched = applyPatchToBook(patch, bookDetails);

			if (bookPatched != null) {

				Book b = bookRepoInterface.save(bookPatched);
				res.setObject(b);
				res.setMessage(AppConst.ResponseMessages.BOOK_UPDATED);
				res.setHttpStatus(HttpStatus.OK);
			}

		} catch (RuntimeException ex) {
			res.setMessage(AppConst.ResponseMessages.BOOK_NOT_EXISTS);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			res.setMessage(AppConst.ResponseMessages.INTERNAL_SERVER_ERROR);
			res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return res;
	}

	private Book applyPatchToBook(JsonPatch patch, Book bookDetails) {

		JsonNode patched;
		try {

			patched = patch.apply(objectMapper.convertValue(bookDetails, JsonNode.class));
			return objectMapper.treeToValue(patched, Book.class);

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (JsonPatchException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return null;
	}
}
