package com.topnotch.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.topnotch.demo.models.User;

public interface UserRepository extends JpaRepository<User, String>{

}
