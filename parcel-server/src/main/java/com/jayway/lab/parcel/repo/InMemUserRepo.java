package com.jayway.lab.parcel.repo;

import java.util.ArrayList;
import java.util.List;

import com.jayway.lab.parcel.model.User;

public class InMemUserRepo implements UserRepo {
	
	private List<User> users;
	
	public InMemUserRepo() {
		users = new ArrayList<User>();
	}
	
	@Override
	public List<User> getAllUsers() {
		return users;
	}

	@Override
	public void addUser(User user) {
		users.add(user);
	}
	
	@Override
	public User getUserById(String userId) {
		for(User user: users) {
			if (userId.equals(user.getId()))
				return user;
		}
		return null;
	}
	
	@Override
	public List<User> getUsersWatchingParcel(String parcelId) {
		List<User> result = new ArrayList<User>();
		for(User user: users) {
			if (user.isWatching(parcelId))
				result.add(user);
		}
		return result;
	}

}
