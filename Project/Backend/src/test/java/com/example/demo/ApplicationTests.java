package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.User;
import com.repository.UserRepository;
import com.util.LoginRequest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;

@SpringBootTest //test of a spring boot application always springboot test class
@AutoConfigureMockMvc
public class ApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserRepository userRepository;

	@Test //test one cana user Sign in?
	public void testUserSignUp() throws Exception {
		User newUser = new User("testuser", "testuser@example.com", "password123");

		// Simulate the sign-up process
		mockMvc.perform(post("/api/auth/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(newUser)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message").value("User registered successfully!"));
	}

	@Test
	public void testUserSignUpAndSignIn() throws Exception { //test 2 can a user sign up encountering expected issue
		// User details
		String username = "testuser";
		String password = "password123";
		String email = "testuser@example.com";
	
		// Sign-Up
		User newUser = new User(username, email, password);
		mockMvc.perform(post("/api/auth/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(newUser)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message").value("User registered successfully!"));
	
		// Sign-In
		LoginRequest loginRequest = new LoginRequest(username, password);
		mockMvc.perform(post("/api/auth/signin")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(loginRequest)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.token").exists()); // Assuming the API returns a token
	}
	

}
