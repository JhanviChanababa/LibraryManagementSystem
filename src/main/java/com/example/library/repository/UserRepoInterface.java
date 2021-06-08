package com.example.library.repository;

import com.example.library.model.User;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepoInterface extends JpaRepository<User, UUID> {

	@Query("select user from User user where user.email = :email and user.password = :password")
	User authenticateUser(@Param("email") String email, @Param("password") String password);
}
