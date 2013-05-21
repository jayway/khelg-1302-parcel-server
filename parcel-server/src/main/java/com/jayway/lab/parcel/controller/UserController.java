package com.jayway.lab.parcel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jayway.lab.parcel.model.User;
import com.jayway.lab.parcel.repo.ParcelRepo;
import com.jayway.lab.parcel.repo.UserRepo;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ParcelRepo parcelRepo;
	
	@RequestMapping(method=RequestMethod.GET, value="")
	public @ResponseBody List<User> getUsers() {
		return userRepo.getAllUsers();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public @ResponseBody User getUser(@PathVariable String id) {
		return userRepo.getUserById(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/{id}")
	public @ResponseBody User createUser(@PathVariable String id) {
		User user = new User(id);
		userRepo.addUser(user);
		return user;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/{userId}/watch")
	public @ResponseBody void watchPackage(@PathVariable String userId, @RequestParam String parcelid) {
		User user = userRepo.getUserById(userId);
		user.watch(parcelid);
	}

}
