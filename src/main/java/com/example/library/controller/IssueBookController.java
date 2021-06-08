package com.example.library.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.model.IssuedBook;
import com.example.library.model.Response;
import com.example.library.service.IssueBookService;
import com.example.library.utils.AppConst;
import com.github.fge.jsonpatch.JsonPatch;

@RestController
public class IssueBookController {

	@Autowired
	IssueBookService issueBookService;

	private static final String PATH = "/issueBook";
	private static final String RES_MESSAGE = "message";
	private static final String RES_BODY = "issuedBook";

	// Get all issued books
	@RequestMapping(value = PATH, method = RequestMethod.GET)
	public ResponseEntity<List<IssuedBook>> getBooks() {
		List<IssuedBook> issuedBooks = issueBookService.getIssuedBooks();
		return new ResponseEntity<List<IssuedBook>>(issuedBooks, HttpStatus.OK);
	}

	// Issue a book
	@RequestMapping(value = PATH, method = RequestMethod.POST)
	public ResponseEntity<Map> addBookIssued(@RequestBody IssuedBook bookIssued) {
		Response<IssuedBook> res = issueBookService.addIssuedBook(bookIssued);
		return new ResponseEntity<Map>(Map.of(RES_MESSAGE, res.getMessage(), RES_BODY, res.getObject()),
				res.getHttpStatus());
	}

	// Update an issued book
	@RequestMapping(value = PATH, method = RequestMethod.PUT)
	public ResponseEntity<Map> updateBookIssued(@RequestBody IssuedBook bookIssued) {
		Response<IssuedBook> res = issueBookService.updateIssuedBook(bookIssued);
		return new ResponseEntity<Map>(Map.of(RES_MESSAGE, res.getMessage(), RES_BODY, res.getObject()),
				res.getHttpStatus());
	}

	// Update details of an issued book 
	@RequestMapping(value = PATH, method = RequestMethod.PATCH, consumes = AppConst.ResponseMessages.JSON_PATCH)
	public ResponseEntity<Map> updateBookIssued(@RequestParam UUID id, @RequestBody JsonPatch patch) {
		Response<IssuedBook> res = issueBookService.updateIssuedBookDetails(id, patch);
		return new ResponseEntity<Map>(Map.of(RES_MESSAGE, res.getMessage(), RES_BODY, res.getObject()),
				res.getHttpStatus());
	}
}
