package com.example.library.repository;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.library.model.IssuedBook;

@Repository
public class IssueBookRepo {

	@PersistenceContext
	private EntityManager entityMngr;

	@Transactional
	public void addBookIssued(IssuedBook bookIssued) {

		try {
			entityMngr.createNativeQuery("UPDATE book SET is_issued = true where id=?")
					.setParameter(1, bookIssued.getBook().getId()).executeUpdate();
		} catch (javax.persistence.PersistenceException ex) {

			System.out.println("Exception in addBookIssued:" + ex.getMessage());

		}

	}

	@Transactional
	public boolean updateBookIssued(IssuedBook bookIssued) {

		try {

			Date dt = new Date();
			Date utilDate = new Date(dt.getTime());
			if (bookIssued.getActual_return_date() != null && bookIssued.getActual_return_date().before(utilDate)) {
				entityMngr.createNativeQuery("UPDATE book SET is_issued = false where id=?")
						.setParameter(1, bookIssued.getBook().getId()).executeUpdate();
			}

			return true;

		} catch (javax.persistence.PersistenceException ex) {

			System.out.println("Exception in updateBookIssued:" + ex.getMessage());

		}

		return false;

	}
}
