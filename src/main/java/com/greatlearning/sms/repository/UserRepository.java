package com.greatlearning.sms.repository;

import com.greatlearning.sms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
  import org.springframework.data.jpa.repository.Query;


  public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.username = ?1")
    public User getUserByUsername(String username);

  }     
