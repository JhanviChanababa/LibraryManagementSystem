package com.example.library.service;

import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.library.model.Book;
import com.example.library.model.IssuedBook;
import com.example.library.model.Response;
import com.example.library.model.User;
import com.example.library.repository.BookRepoInterface;
import com.example.library.repository.IssueBookRepo;
import com.example.library.repository.IssueBookRepoInterface;
import com.example.library.repository.UserRepoInterface;
import com.example.library.utils.AppConst;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

@Service
public class IssueBookService {

	@Autowired
	IssueBookRepo issuedBookRepo;

	@Autowired
	IssueBookRepoInterface issueBookInterface;

	@Autowired
	UserRepoInterface userRepoInterface;

	@Autowired
	BookRepoInterface bookRepoInterface;

	private ObjectMapper objectMapper = new ObjectMapper();

	public Response<IssuedBook> addIssuedBook(IssuedBook issueBook) {

		Response<IssuedBook> res = new Response<IssuedBook>();

		try {

			Optional<User> user = userRepoInterface.findById(issueBook.getUser().getId());
			Optional<Book> book = bookRepoInterface.findById(issueBook.getBook().getId());

			if (user.isPresent() && book.isPresent()) {

				issueBook.setExpected_return_date(
						Timestamp.from(issueBook.getIssued_date().toInstant().plus(7, ChronoUnit.DAYS)));
				IssuedBook issuedBook = issueBookInterface.save(issueBook);

				if (issuedBook == null) {
					res.setMessage(AppConst.INTERNAL_SERVER_ERROR);
					res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
				} else {

					issuedBookRepo.addBookIssued(issuedBook);
					res.setObject(issuedBook);
					res.setMessage(AppConst.ISSUE_BOOK_ADDED);
					res.setHttpStatus(HttpStatus.OK);
				}

			} else {
				res.setMessage(AppConst.USERID_BOOKID_NOT_EXISTS);
				res.setHttpStatus(HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {

			res.setMessage(AppConst.INTERNAL_SERVER_ERROR);
			res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}

		return res;
	}

	public List<IssuedBook> getIssuedBooks() {
		return issueBookInterface.getIssuedBooks();
	}

	public Response<IssuedBook> updateIssuedBook(IssuedBook issueBook) {

		Response<IssuedBook> res = new Response<IssuedBook>();

		try {

			Optional<User> user = userRepoInterface.findById(issueBook.getUser().getId());
			Optional<Book> book = bookRepoInterface.findById(issueBook.getBook().getId());

			if (user.isPresent() && book.isPresent()) {

				IssuedBook issuedBook = issueBookInterface.save(issueBook);

				if (issuedBook == null) {
					res.setMessage(AppConst.INTERNAL_SERVER_ERROR);
					res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
				} else {

					issuedBookRepo.updateBookIssued(issuedBook);
					res.setObject(issuedBook);
					res.setMessage(AppConst.ISSUE_BOOK_UPDATED);
					res.setHttpStatus(HttpStatus.OK);
				}

			} else {
				res.setMessage(AppConst.USERID_BOOKID_NOT_EXISTS);
				res.setHttpStatus(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {

			res.setMessage(AppConst.INTERNAL_SERVER_ERROR);
			res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}

		return res;
	}

	public Response<IssuedBook> updateIssuedBookDetails(UUID id, JsonPatch patch) {

		Response<IssuedBook> res = new Response<IssuedBook>();

		try {

			IssuedBook bookDetails = issueBookInterface.findById(id).orElseThrow(RuntimeException::new);
			IssuedBook bookPatched = applyPatchToBook(patch, bookDetails);

			if (bookPatched == null) {
				res.setMessage(AppConst.INTERNAL_SERVER_ERROR);
				res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				
				IssuedBook issuedBook = issueBookInterface.save(bookPatched);
				issuedBookRepo.updateBookIssued(issuedBook);
				res.setObject(issuedBook);
				res.setMessage(AppConst.ISSUE_BOOK_UPDATED);
				res.setHttpStatus(HttpStatus.OK);
				
			}

		} catch (RuntimeException ex) {
			res.setMessage(AppConst.ISSUE_BOOK_NOT_EXISTS);
			res.setHttpStatus(HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			res.setMessage(AppConst.INTERNAL_SERVER_ERROR);
			res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return res;
	}

	private IssuedBook applyPatchToBook(JsonPatch patch, IssuedBook bookDetails) {

		JsonNode patched;

		try {

			patched = patch.apply(objectMapper.convertValue(bookDetails, JsonNode.class));
			return objectMapper.treeToValue(patched, IssuedBook.class);

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
