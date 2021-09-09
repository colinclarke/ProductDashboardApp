package com.colin;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

import com.colin.models.Category;
import com.colin.models.Product;
import com.colin.models.Role;
import com.colin.models.User;
import com.colin.repo.ProductRepository;
import com.colin.repo.UserRepository;
import com.colin.service.ProductService;
import com.colin.service.UserDetailsServiceImpl;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTest {

	@MockBean
	UserRepository userRepository;
	
	@Autowired
	UserDetailsServiceImpl userService;
	
	@Test
	public void testSaveProduct() {
		Set<Role> roles = new HashSet<>();
		Role r = new Role();
		r.setName("ROLE_USER");
		roles.add(r);
		User u = new User(1, "Percy", "pepperbox", roles, true, new ArrayList<>());

		
		when(userRepository.save(u)).thenReturn(u);
		
		userService.createNewUser(u);
		assertNotNull(u);
	}
}
