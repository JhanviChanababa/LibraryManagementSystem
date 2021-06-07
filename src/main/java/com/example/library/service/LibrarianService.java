package com.example.library.service;

import java.util.Optional;
import java.util.UUID;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.library.model.Librarian;
import com.example.library.model.Response;
import com.example.library.repository.LibrarianRepoInterface;
import com.example.library.utils.AppConst;
import com.example.library.utils.Utility;

@Service
public class LibrarianService {

	@Autowired
	LibrarianRepoInterface libRepoInterface;

	public Librarian authenticateUser(Librarian librarian) {

		String hashedPassword = Utility.encryptPassword(librarian.getPassword());

		Librarian lib = libRepoInterface.authenticateUser(librarian.getEmail(), hashedPassword);
		if (lib != null) {
			return lib;
		}

		return null;
	}

	public Response<Librarian> addLibrarian(Librarian librarian) {

		Response<Librarian> res = new Response<Librarian>();

		try {

			String hashedPassword = Utility.encryptPassword(librarian.getPassword());
			librarian.setPassword(hashedPassword);

			Librarian l = libRepoInterface.save(librarian);

			if (l == null) {
				res.setMessage(AppConst.INTERNAL_SERVER_ERROR);
				res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			} else {

				res.setObject(l);
				res.setMessage(AppConst.LIBRARIAN_ADDED);
				res.setHttpStatus(HttpStatus.OK);
			}

		} catch (Exception e) {

			if (e.getCause() instanceof ConstraintViolationException) {
				res.setMessage(AppConst.LIBRARIAN_EMAIL_EXISTS);
				res.setHttpStatus(HttpStatus.CONFLICT);
			} else {
				res.setMessage(AppConst.INTERNAL_SERVER_ERROR);
				res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return res;
	}

	public Response<Librarian> getLibrarian(UUID id) {

		Response<Librarian> res = new Response<Librarian>();

		try {

			Librarian librarian = libRepoInterface.findById(id).orElseThrow(RuntimeException::new);
			res.setObject(librarian);
			res.setMessage(AppConst.GET_LIBRARIAN);
			res.setHttpStatus(HttpStatus.OK);

		} catch (RuntimeException ex) {
			res.setMessage(AppConst.LIBRARIAN_EXISTS);
			res.setHttpStatus(HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			res.setMessage(AppConst.INTERNAL_SERVER_ERROR);
			res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return res;
	}

	public Response<Librarian> updateLibrarian(Librarian librarian) {

		Response<Librarian> res = new Response<Librarian>();

		try {

			String hashedPassword = Utility.encryptPassword(librarian.getPassword());
			librarian.setPassword(hashedPassword);

			Optional<Librarian> findLibrarian = libRepoInterface.findById(librarian.getId());

			if (findLibrarian.isPresent()) {

				Librarian l = libRepoInterface.save(librarian);

				if (l == null) {
					res.setMessage(AppConst.INTERNAL_SERVER_ERROR);
					res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
				} else {
					res.setObject(l);
					res.setMessage(AppConst.LIBRARIAN_UPDATED);
					res.setHttpStatus(HttpStatus.OK);
				}

			} else {
				res.setMessage(AppConst.LIBRARIAN_EXISTS);
				res.setHttpStatus(HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			res.setMessage(AppConst.INTERNAL_SERVER_ERROR);
			res.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return res;
	}
}
