package com.example.library.controller;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.library.model.Response;
import com.example.library.model.User;
import com.example.library.repository.UserRepoInterface;
import com.example.library.service.UserService;
import com.example.library.utils.AppConst;
import com.example.library.utils.Utility;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	UserRepoInterface userRepoInterface;

	private static final String PATH = "/user";
	private static final String RES_MESSAGE = "message";
	private static final String RES_BODY = "user";

	// Get a user by id
	@RequestMapping(value = PATH, method = RequestMethod.GET)
	public ResponseEntity<Map> getUser(@RequestParam UUID id) {
		Response<User> res = userService.getUser(id);
		return new ResponseEntity<Map>(Map.of(RES_MESSAGE, res.getMessage(), RES_BODY, res.getObject()),
				res.getHttpStatus());
	}

	// Add a user
	@RequestMapping(value = PATH, method = RequestMethod.POST)
	public ResponseEntity<Map> addUser(@RequestBody User user) {

		Response<User> res = userService.addUser(user);
		String token = Utility.getJWTToken(user.getName());
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Authorization", token);
		return new ResponseEntity<Map>(Map.of(RES_MESSAGE, res.getMessage(), RES_BODY, res.getObject()),
				responseHeaders, res.getHttpStatus());
	}

	// User login
	@RequestMapping(value = PATH + "/login", method = RequestMethod.POST)
	public ResponseEntity<Map> loginUser(@RequestBody User user) {

		User authicatedUser = userService.authenticateUser(user);
		String token = Utility.getJWTToken(user.getEmail());
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Authorization", token);

		if (authicatedUser == null) {
			return new ResponseEntity<Map>(Map.of(RES_MESSAGE, AppConst.ResponseMessages.UNAUTHORIZED_USER),
					HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<Map>(
				Map.of(RES_MESSAGE, AppConst.ResponseMessages.LOGGEDIN_USER, RES_BODY, authicatedUser), responseHeaders,
				HttpStatus.OK);
	}

	// Update a user
	@RequestMapping(value = PATH, method = RequestMethod.PUT)
	public ResponseEntity<Map> updateUser(@RequestBody User user) {
		Response<User> res = userService.updateUser(user);
		return new ResponseEntity<Map>(Map.of(RES_MESSAGE, res.getMessage(), RES_BODY, res.getObject()),
				res.getHttpStatus());
	}
}
