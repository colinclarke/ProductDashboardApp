package com.colin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

	public boolean createNewUser(User u) {

		if (userRepository.getUserByUsername(u.getUsername()) != null) {
			return false;
		}

		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		String encryptP = b.encode(u.getPassword());
		u.setPassword(encryptP);

		u.setEnabled(true);

		userRepository.save(u);
		return true;
	}

	public User findById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

}
