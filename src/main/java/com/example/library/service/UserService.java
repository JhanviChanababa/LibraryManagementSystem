package com.example.library.service;

import java.util.Optional;
import java.util.UUID;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.library.model.Response;
import com.example.library.model.User;
import com.example.library.repository.UserRepoInterface;
import com.example.library.utils.AppConst;
import com.example.library.utils.Utility;

@Service
public class UserService {

	@Autowired
	UserRepoInterface userRepoInterface;
	
	public Response<User> getUser(UUID id) {

		Response<User> res = new Response<User>();

		try {

			User user = userRepoInterface.findById(id).orElseThrow(RuntimeException::new);
			res.setObject(user);
			res.setMessage(AppConst.GET_USER);
			res.setHttpStatus(HttpStatus.OK);

		} catch (RuntimeException ex) {
			res.setMessage(AppConst.USER_EXISTS);
			res.setHttpStatus(HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			res.setMessage(AppConst.INTERNAL_SERVER_ERROR);
			res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return res;
	}

	public Response<User> addUser(User user) {

		Response<User> res = new Response<User>();
		
		try {

			String hashedPassword = Utility.encryptPassword(user.getPassword());
			user.setPassword(hashedPassword);
			User u = userRepoInterface.save(user);

			if (u == null) {
				res.setMessage(AppConst.INTERNAL_SERVER_ERROR);
				res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				res.setObject(u);
				res.setMessage(AppConst.USER_ADDED);
				res.setHttpStatus(HttpStatus.OK);
			}

		} catch (Exception e) {

			if (e.getCause() instanceof ConstraintViolationException) {
				res.setMessage(AppConst.USER_EMAIL_EXISTS);
				res.setHttpStatus(HttpStatus.CONFLICT);
			} else {
				res.setMessage(AppConst.INTERNAL_SERVER_ERROR);
				res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return res;
	}

	public User authenticateUser(User user) {

		String hashedPassword = Utility.encryptPassword(user.getPassword());

		User authenticatedUser = userRepoInterface.authenticateUser(user.getEmail(), hashedPassword);
		if (authenticatedUser != null) {
			return authenticatedUser;
		}

		return null;
	}

	public Response<User> updateUser(User user) {

		Response<User> res = new Response<User>();

		try {

			String hashedPassword = Utility.encryptPassword(user.getPassword());
			user.setPassword(hashedPassword);

			Optional<User> findUser = userRepoInterface.findById(user.getId());

			if (findUser.isPresent()) {

				User updatedUser = userRepoInterface.save(user);

				if (updatedUser == null) {
					res.setMessage(AppConst.INTERNAL_SERVER_ERROR);
					res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
				} else {

					res.setObject(updatedUser);
					res.setMessage(AppConst.USER_UPDATED);
					res.setHttpStatus(HttpStatus.OK);

				}

			} else {
				res.setMessage(AppConst.USER_EXISTS);
				res.setHttpStatus(HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			res.setMessage(AppConst.INTERNAL_SERVER_ERROR);
			res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return res;
	}
}