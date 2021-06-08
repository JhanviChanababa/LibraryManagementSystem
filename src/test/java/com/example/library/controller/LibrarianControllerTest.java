package com.example.library.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

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

import com.example.library.matchers.LibraryMatcher;
import com.example.library.model.Librarian;
import com.example.library.model.Response;
import com.example.library.service.LibrarianService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(LibrarianController.class)
public class LibrarianControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	private LibrarianService librarianService;

	@InjectMocks
	private LibrarianController librarianController;

	private static final String PATH = "/librarian";

	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(librarianController).build();
	}

	@Test
	public void testRegisterlibrarian() throws Exception {

		Response<Librarian> res = new Response<Librarian>();

		EasyRandom easyRandom = new EasyRandom();

		Librarian librarian = easyRandom.nextObject(Librarian.class);

		MockHttpServletRequestBuilder requestBuilder = post(PATH).accept(MediaType.APPLICATION_JSON_UTF8)
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(objectMapper.writeValueAsString(librarian));

		res.setObject(librarian);
		res.setMessage("");
		res.setHttpStatus(HttpStatus.OK);

		when(librarianService.addLibrarian(Mockito.argThat(new LibraryMatcher(librarian)))).thenReturn(res);

		mockMvc.perform(requestBuilder).andExpect(status().isOk());
	}

	@Test
	public void testLoginlibrarian() throws Exception {

		Response<Librarian> res = new Response<Librarian>();

		EasyRandom easyRandom = new EasyRandom();

		Librarian librarian = easyRandom.nextObject(Librarian.class);

		MockHttpServletRequestBuilder requestBuilder = post(PATH + "/login").accept(MediaType.APPLICATION_JSON_UTF8)
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(objectMapper.writeValueAsString(librarian));

		res.setObject(librarian);
		res.setMessage("");
		res.setHttpStatus(HttpStatus.OK);

		when(librarianService.authenticateUser(Mockito.argThat(new LibraryMatcher(librarian)))).thenReturn(librarian);

		mockMvc.perform(requestBuilder).andExpect(status().isOk());

	}

	@Test
	public void testUpdatelibrarian() throws Exception {

		Response<Librarian> res = new Response<Librarian>();

		EasyRandom easyRandom = new EasyRandom();

		Librarian librarian = easyRandom.nextObject(Librarian.class);

		MockHttpServletRequestBuilder requestBuilder = put(PATH).accept(MediaType.APPLICATION_JSON_UTF8)
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(objectMapper.writeValueAsString(librarian));

		res.setObject(librarian);
		res.setMessage("");
		res.setHttpStatus(HttpStatus.OK);

		when(librarianService.updateLibrarian(Mockito.argThat(new LibraryMatcher(librarian)))).thenReturn(res);

		mockMvc.perform(requestBuilder).andExpect(status().isOk());

	}

	@Test
	public void getlibrarian() throws Exception {

		UUID uuid = UUID.randomUUID();

		MockHttpServletRequestBuilder requestBuilder = get(PATH).param("id", uuid.toString())
				.accept(MediaType.APPLICATION_JSON_UTF8).contentType(MediaType.APPLICATION_JSON_UTF8);

		Librarian lib = new Librarian();
		Response<Librarian> res = new Response<>();
		res.setObject(lib);
		res.setMessage("");
		res.setHttpStatus(HttpStatus.OK);
		when(librarianService.getLibrarian(uuid)).thenReturn(res);

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
