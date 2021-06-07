package com.example.library.model;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "book_issued")
public class IssuedBook {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "book_id", referencedColumnName = "id")
	private Book book;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Timestamp issued_date;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Timestamp expected_return_date;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Timestamp actual_return_date;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Timestamp getIssued_date() {
		return issued_date;
	}

	public void setIssued_date(Timestamp issued_date) {
		this.issued_date = issued_date;
	}

	public Timestamp getExpected_return_date() {
		return expected_return_date;
	}

	public void setExpected_return_date(Timestamp expected_return_date) {
		this.expected_return_date = expected_return_date;
	}

	public Timestamp getActual_return_date() {
		return actual_return_date;
	}

	public void setActual_return_date(Timestamp actual_return_date) {
		this.actual_return_date = actual_return_date;
	}

}
