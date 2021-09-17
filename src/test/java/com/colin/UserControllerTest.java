package com.colin;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doReturn;
import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.colin.controller.LandingPageController;
import com.colin.controller.ProductController;
import com.colin.models.Category;
import com.colin.models.DummyUser;
import com.colin.models.Product;
import com.colin.models.ProductCategory;
import com.colin.models.Role;
import com.colin.models.User;
import com.colin.repo.CategoryRepository;
import com.colin.service.ProductService;
import com.colin.service.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
	
ObjectMapper map;
	
	@MockBean
	UserDetailsServiceImpl userDetails;
	
	
	@InjectMocks
	LandingPageController controllerUnderTest; 
	
	private MockMvc mvc;
	
	@BeforeEach
	public void contextLoads() {
		map = new ObjectMapper();
		
		MockitoAnnotations.openMocks(this);
		
		this.mvc = MockMvcBuilders.standaloneSetup(controllerUnderTest).build();
		
	}

	@Test
	void createNewUser() throws Exception {
		
		
		
		DummyUser user = new DummyUser("Percy", "pepperbox", new ArrayList<>());
		boolean newUser = true;
		
		when(userDetails.createNewUser(user)).thenReturn(newUser);
		
		mvc.perform(post("/api/create-new-user")
				.content(toJsonString(user))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isAccepted());
	}
	
	@Test
	void createNewUserFail() throws Exception {
		
		
		
		DummyUser user = new DummyUser("Percy", "pepperbox", new ArrayList<>());
		boolean newUser = false;
		
		when(userDetails.createNewUser(user)).thenReturn(newUser);
		
		mvc.perform(post("/api/create-new-user")
				.content(toJsonString(user))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isConflict());
	}
	
	public static String toJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
