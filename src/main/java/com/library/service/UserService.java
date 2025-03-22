package com.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.entity.User;
import com.library.repo.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;

	public User addUser(User user) {
		return userRepo.save(user);
	}

	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	public Optional<User> getUserById(int id) {
		return userRepo.findById(id);
	}

	public User updateUser(int id, User user) {
		Optional<User> theUser = userRepo.findById(id);
		User newUser = theUser.get();
		newUser.setUname(user.getUname());
		newUser.setUemail(user.getUemail());
		return userRepo.save(newUser);
	}
	public void deleteUser(int id) {
		userRepo.deleteById(id);
	}

}
