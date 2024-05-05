package com.greatlearning.sms.config;

import com.greatlearning.sms.service.impl.SmsUserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
  public class SmsSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
      return new SmsUserDetailsServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {

      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

      authProvider.setUserDetailsService(userDetailsService());
      authProvider.setPasswordEncoder(passwordEncoder());

      return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

      http.authorizeHttpRequests(auth ->{
                auth.requestMatchers("/","/student/list", "/student/save").hasAnyAuthority("NORMAL_USER","ADMIN_USER")
                        .requestMatchers("/student/update","/student/delete").hasAuthority("ADMIN_USER")
                        .anyRequest().authenticated();
              })
              .logout(logout->{
                logout.logoutSuccessUrl("/login")
                        .permitAll();
              })
              .exceptionHandling(exceptionHandler -> {
                exceptionHandler.accessDeniedPage("/student/403");
              })
              .formLogin((formLogin) ->
                      formLogin
                              .loginProcessingUrl("/login")
                              .successForwardUrl("/student/list")
                              .permitAll()
              )
              .cors(AbstractHttpConfigurer::disable)
              .csrf(AbstractHttpConfigurer::disable);

      http.authenticationProvider(daoAuthenticationProvider());
      return http.build();
    }

  } 
