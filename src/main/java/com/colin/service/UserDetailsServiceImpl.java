package com.colin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.colin.models.DummyUser;
import com.colin.models.User;
import com.colin.repo.RoleRepository;
import com.colin.repo.UserRepository;
import com.colin.security.MyUserDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.getUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Could not find user");
		}
		return new MyUserDetails(user);
	}

	public boolean createNewUser(DummyUser u) {

		if (userRepository.getUserByUsername(u.getUsername()) != null) {
			return false;
		}

		User u2 = new User();
		u2.setUsername(u.getUsername());
		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		String encryptP = b.encode(u.getPassword());
		u2.setPassword(encryptP);

		u2.setEnabled(true);
		
		if (u.getRoles().isEmpty()) {
			u2.addRole(roleRepository.getRoleByName("ROLE_USER"));
		}
		
		for (String s : u.getRoles()) {
			u2.addRole(roleRepository.getRoleByName(s));
		}

		userRepository.save(u2);
		return true;
	}

	public User findById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

}
