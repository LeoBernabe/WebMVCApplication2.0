package com.lgeratech.demo.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lgeratech.demo.models.User;
import com.lgeratech.demo.models.UserAuthDetails;
import com.lgeratech.demo.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
		@Autowired
		UserRepository userRepository;
		@Override
		public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		    Optional<User> user = userRepository.findByUserName(userName);
	    	user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
			List<String> roles = Arrays.stream(user.get().getRoles().split(",")).map(String::new)
                    .collect(Collectors.toList());
			return new UserAuthDetails(user.get(), roles);
		}
}
 