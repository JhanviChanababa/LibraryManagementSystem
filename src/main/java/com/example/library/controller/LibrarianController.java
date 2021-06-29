package com.example.library.controller;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.model.Librarian;
import com.example.library.model.Response;
import com.example.library.service.LibrarianService;
import com.example.library.utils.AppConst;
import com.example.library.utils.Utility;

@RestController
public class LibrarianController {

	@Autowired
	private LibrarianService librarianService;

	private static final String PATH = "/librarian";
	private static final String RES_MESSAGE = "message";
	private static final String RES_BODY = "librarian";

	// Get a librarian
	@RequestMapping(value = PATH + "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Map> getLibrarian(@PathVariable UUID id) {
		Response<Librarian> res = librarianService.getLibrarian(id);
		return new ResponseEntity<Map>(Map.of(RES_MESSAGE, res.getMessage(), RES_BODY, res.getObject()),
				res.getHttpStatus());
	}

	// Add a librarian
	@RequestMapping(value = PATH, method = RequestMethod.POST)
	public ResponseEntity<Map> addUser(@RequestBody Librarian librarian) {

		Response<Librarian> res = librarianService.addLibrarian(librarian);
		String token = Utility.getJWTToken(librarian.getName());
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Authorization", token);
		return new ResponseEntity<Map>(Map.of(RES_MESSAGE, res.getMessage(), RES_BODY, res.getObject()),
				responseHeaders, res.getHttpStatus());

	}

	// Librarian login
	@RequestMapping(value = PATH + "/login", method = RequestMethod.POST)
	public ResponseEntity<Map> loginUser(@RequestBody Librarian librarian) {

		Librarian authicatedUser = librarianService.authenticateUser(librarian);
		String token = Utility.getJWTToken(librarian.getEmail());
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Authorization", token);

		if (authicatedUser == null) {
			return new ResponseEntity<Map>(Map.of(RES_MESSAGE, AppConst.ResponseMessages.UNAUTHORIZED_LIBRARIAN),
					HttpStatus.UNAUTHORIZED);

		}

		return new ResponseEntity<Map>(
				Map.of(RES_MESSAGE, AppConst.ResponseMessages.LOGGEDIN_LIBRARIAN, RES_BODY, authicatedUser),
				responseHeaders, HttpStatus.OK);
	}

	// Update a librarian
	@RequestMapping(value = PATH + "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Map> updateLibrarian(@PathVariable UUID id, @RequestBody Librarian lib) {
		Response<Librarian> res = librarianService.updateLibrarian(id, lib);
		return new ResponseEntity<Map>(Map.of(RES_MESSAGE, res.getMessage(), RES_BODY, res.getObject()),
				res.getHttpStatus());
	}
}
