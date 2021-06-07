package com.example.library.model;

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

@Entity
@Table(name = "book")
public class Book {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;
	
	@Column(unique = true)
	private Long isbn;
	
	private String name;
	
	private String author_name;
	
	private boolean is_issued;
	
	private boolean is_active;
	
	@OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "librarian_id", referencedColumnName = "id")
	private Librarian librarian;
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Long getISBN() {
		return isbn;
	}

	public void setISBN(Long iSBN) {
		isbn = iSBN;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor_name() {
		return author_name;
	}

	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}

	public boolean isIssued() {
		return is_issued;
	}

	public void setIssued(boolean isIssued) {
		this.is_issued = isIssued;
	}

	public boolean isActive() {
		return is_active;
	}

	public void setActive(boolean isActive) {
		this.is_active = isActive;
	}

	public Librarian getLibrarian() {
		return librarian;
	}

	public void setLibrarian(Librarian librarian) {
		this.librarian = librarian;
	}
	
	
}
