package com.greatlearning.sms.service.impl;

import com.greatlearning.sms.entity.User;
import com.greatlearning.sms.entity.UserDetails;
import com.greatlearning.sms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

  public class SmsUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

      User user = userRepository.getUserByUsername(username);

      if (user == null) {
        throw new UsernameNotFoundException("Could not find user");
      }

      return new UserDetails(user);
    }
  } 
