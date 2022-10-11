package com.example.demo.service;

import com.example.demo.model.User1;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class User1Service implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User1 user = userRepository.findByUsername(username);
		if(user == null) {
			return null;
		}
//		return User.builder()
//				.username(user.getEmail())
//				.password(user.getPassword())
//				.roles(user.getRole().toString())
//				.build();
		return MyUser.builder()
				.username(user.getUsername())
				.password(user.getPassword())
				.roles(user.getRole().toString())
				.build();
	}
}

