package com.example.library.controller;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	private static final String path="/librarian";

	@RequestMapping(value = path, method = RequestMethod.GET)
	public ResponseEntity<Map> getLibrarian(@RequestParam UUID id) {

		Response<Librarian> res = librarianService.getLibrarian(id);
		return new ResponseEntity<Map>(Map.of("message", res.getMessage(),"librarian", res.getObject()), res.getHttpStatus());

	}

	@RequestMapping(value = path, method = RequestMethod.POST)
	public ResponseEntity<Map> addUser(@RequestBody Librarian librarian) {

		Response<Librarian> res = librarianService.addLibrarian(librarian);
		String token = Utility.getJWTToken(librarian.getName());
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Authorization", token);
		return new ResponseEntity<Map>(Map.of("message", res.getMessage(),"librarian", res.getObject()), responseHeaders, res.getHttpStatus());

	}

	@RequestMapping(value = path+"/login", method = RequestMethod.POST)
	public ResponseEntity<Map> loginUser(@RequestBody Librarian librarian) {

		Librarian authicatedUser = librarianService.authenticateUser(librarian);
		String token = Utility.getJWTToken(librarian.getEmail());
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Authorization", token);
		
		if(authicatedUser == null) {
			return new ResponseEntity<Map>(Map.of("message", AppConst.UNAUTHORIZED_LIBRARIAN), HttpStatus.UNAUTHORIZED);

		}
		
		return new ResponseEntity<Map>(Map.of("message", AppConst.LOGGEDIN_LIBRARIAN), responseHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = path, method = RequestMethod.PUT)
	public ResponseEntity<Map> updateLibrarian(@RequestBody Librarian lib) {

		Response<Librarian> res = librarianService.updateLibrarian(lib);
		return new ResponseEntity<Map>(Map.of("message", res.getMessage(),"librarian", res.getObject()), res.getHttpStatus());

	}
}
