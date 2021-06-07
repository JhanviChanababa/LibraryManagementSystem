package com.example.library.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.jeasy.random.EasyRandom;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.library.matchers.BookMatcher;
import com.example.library.model.Book;
import com.example.library.model.Librarian;
import com.example.library.model.Response;
import com.example.library.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	BookService bookService;

	@MockBean
	Librarian lib;

	@InjectMocks
	BookController bookController;

	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
	}

	@Test
	public void testAddbook() throws Exception {

		Response<Book> res = new Response<>();

		EasyRandom easyRandom = new EasyRandom();

		Book book = easyRandom.nextObject(Book.class);

		MockHttpServletRequestBuilder requestBuilder = post("/book").accept(MediaType.APPLICATION_JSON_UTF8)
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(objectMapper.writeValueAsString(book));

		res.setObject(book);
		res.setMessage("");
		res.setHttpStatus(HttpStatus.OK);

		when(bookService.addBook(Mockito.argThat(new BookMatcher(book)))).thenReturn(res);

		mockMvc.perform(requestBuilder).andExpect(status().isOk());
	}

	@Test
	public void testUpdatebook() throws Exception {

		Response<Book> res = new Response<Book>();

		EasyRandom easyRandom = new EasyRandom();

		Book book = easyRandom.nextObject(Book.class);

		MockHttpServletRequestBuilder requestBuilder = put("/book").accept(MediaType.APPLICATION_JSON_UTF8)
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(objectMapper.writeValueAsString(book));

		res.setObject(book);
		res.setMessage("");
		res.setHttpStatus(HttpStatus.OK);

		when(bookService.updateBook(Mockito.argThat(new BookMatcher(book)))).thenReturn(res);

		mockMvc.perform(requestBuilder).andExpect(status().isOk());

	}

	@Test
	public void getbook() throws Exception {

		MockHttpServletRequestBuilder requestBuilder = get("/book").param("searchString", "")
				.accept(MediaType.APPLICATION_JSON_UTF8).contentType(MediaType.APPLICATION_JSON_UTF8);

		when(bookService.getBooks("")).thenReturn(Arrays.asList());

		mockMvc.perform(requestBuilder).andExpect(status().isOk());

	}

	@Test
	public void testDeletebook() throws Exception {

		Response<Book> res = new Response<Book>();

		EasyRandom easyRandom = new EasyRandom();

		Book book = easyRandom.nextObject(Book.class);

		MockHttpServletRequestBuilder requestBuilder = delete("/book").param("bookId", book.getId().toString())
				.accept(MediaType.APPLICATION_JSON_UTF8).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsString(book));

		res.setObject(book);
		res.setMessage("");
		res.setHttpStatus(HttpStatus.OK);

		when(bookService.deleteBook(book.getId())).thenReturn(res);

		mockMvc.perform(requestBuilder).andExpect(status().isOk());

	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
