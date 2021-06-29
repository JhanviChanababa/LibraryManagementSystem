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

import com.example.library.matchers.UserMatcher;
import com.example.library.model.Response;
import com.example.library.model.User;
import com.example.library.repository.UserRepoInterface;
import com.example.library.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	private UserService userService;

	@MockBean
	UserRepoInterface userRepoInterface;

	@InjectMocks
	private UserController userController;

	private static final String PATH = "/user";

	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@Test
	public void testRegisterUser() throws Exception {

		EasyRandom easyRandom = new EasyRandom();

		User user = easyRandom.nextObject(User.class);

		MockHttpServletRequestBuilder requestBuilder = post(PATH).accept(MediaType.APPLICATION_JSON_UTF8)
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(objectMapper.writeValueAsString(user));

		Response<User> res = new Response<>();
		res.setObject(user);
		res.setMessage("");
		res.setHttpStatus(HttpStatus.OK);

		when(userService.addUser(Mockito.argThat(new UserMatcher(user)))).thenReturn(res);

		mockMvc.perform(requestBuilder).andExpect(status().isOk());

	}

	@Test
	public void testLoginUser() throws Exception {

		EasyRandom easyRandom = new EasyRandom();

		User user = easyRandom.nextObject(User.class);
		Mockito.when(userService.authenticateUser(Mockito.argThat(new UserMatcher(user)))).thenReturn(user);

		MockHttpServletRequestBuilder requestBuilder = post(PATH + "/login").accept(MediaType.APPLICATION_JSON_UTF8)
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(objectMapper.writeValueAsString(user));

		mockMvc.perform(requestBuilder).andExpect(status().isOk());

	}

	@Test
	public void testUpdateUser() throws Exception {

		EasyRandom easyRandom = new EasyRandom();

		User user = easyRandom.nextObject(User.class);

		MockHttpServletRequestBuilder requestBuilder = put(PATH).accept(MediaType.APPLICATION_JSON_UTF8)
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(objectMapper.writeValueAsString(user));

		Response<User> res = new Response<>();
		res.setObject(user);
		res.setMessage("");
		res.setHttpStatus(HttpStatus.OK);

		when(userService.updateUser(user.getId(), Mockito.argThat(new UserMatcher(user)))).thenReturn(res);

		mockMvc.perform(requestBuilder).andExpect(status().isOk());

	}

	@Test
	public void getUser() throws Exception {

		UUID uuid = UUID.randomUUID();

		MockHttpServletRequestBuilder requestBuilder = get(PATH).param("id", uuid.toString())
				.accept(MediaType.APPLICATION_JSON_UTF8).contentType(MediaType.APPLICATION_JSON_UTF8);

		User user = new User();
		Response<User> res = new Response<>();
		res.setObject(user);
		res.setMessage("");
		res.setHttpStatus(HttpStatus.OK);
		when(userService.getUser(uuid)).thenReturn(res);

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
