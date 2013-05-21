package com.jayway.lab.parcel.repo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jayway.lab.parcel.model.User;

@Repository
public interface UserRepo {

	List<User> getAllUsers();

	void addUser(User user);

	User getUserById(String userId);
	
	List<User> getUsersWatchingParcel(String parcelId);

}
