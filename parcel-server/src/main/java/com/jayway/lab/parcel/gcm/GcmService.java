package com.jayway.lab.parcel.gcm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jayway.lab.parcel.model.Parcel;
import com.jayway.lab.parcel.model.User;
import com.jayway.lab.parcel.repo.UserRepo;

@Service
public class GcmService {
	
	@Autowired
	private UserRepo userRepo;

	public void notifyStatusChange(Parcel parcel) {
		List<User> users = userRepo.getUsersWatchingParcel(parcel.getId());
		
		// Do something clever
	}

}
