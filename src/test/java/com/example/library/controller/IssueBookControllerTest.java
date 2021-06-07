package com.example.library.controller;

import static org.mockito.Mockito.when;
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

import com.example.library.matchers.IssuedBookMatcher;
import com.example.library.model.IssuedBook;
import com.example.library.model.Response;
import com.example.library.service.IssueBookService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(IssueBookController.class)
public class IssueBookControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
	IssueBookService issueBookService;
	
	@InjectMocks
	IssueBookController issueBookController;
	
	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(issueBookController).build();
	}
	
	@Test
	public void testAddBookIssued() throws Exception {

		Response<IssuedBook> res = new Response<IssuedBook>();

		EasyRandom easyRandom = new EasyRandom();

		IssuedBook book = easyRandom.nextObject(IssuedBook.class);
		
		MockHttpServletRequestBuilder requestBuilder = post("/issueBook")
				.accept(MediaType.APPLICATION_JSON_UTF8).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsString(book));
		
		res.setObject(book);
		res.setMessage("");
		res.setHttpStatus(HttpStatus.OK);

		when(issueBookService.addIssuedBook(Mockito.argThat(new IssuedBookMatcher(book)))).thenReturn(res);

		mockMvc.perform(requestBuilder).andExpect(status().isOk());

	}
	
	@Test
	public void testUpdatebook() throws Exception {

		Response<IssuedBook> res = new Response<IssuedBook>();

		EasyRandom easyRandom = new EasyRandom();

		IssuedBook book = easyRandom.nextObject(IssuedBook.class);
		Mockito.when(issueBookService.addIssuedBook(book)).thenReturn(res);

		MockHttpServletRequestBuilder requestBuilder = put("/issueBook")
				.accept(MediaType.APPLICATION_JSON_UTF8).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsString(book));

		res.setObject(book);
		res.setMessage("");
		res.setHttpStatus(HttpStatus.OK);

		when(issueBookService.updateIssuedBook(Mockito.argThat(new IssuedBookMatcher(book)))).thenReturn(res);
		
		mockMvc.perform(requestBuilder).andExpect(status().isOk());
		

	}
	
	@Test
	public void getIssuedBooks() throws Exception {
		
		MockHttpServletRequestBuilder requestBuilder = get("/issueBook")
				.accept(MediaType.APPLICATION_JSON_UTF8).contentType(MediaType.APPLICATION_JSON_UTF8);

		when(issueBookService.getIssuedBooks()).thenReturn(Arrays.asList());
		
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
