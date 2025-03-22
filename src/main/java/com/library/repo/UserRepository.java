package com.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
