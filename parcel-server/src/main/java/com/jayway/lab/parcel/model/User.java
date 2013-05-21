package com.jayway.lab.parcel.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class User {

	private String id;
	private Set<String> watchedParcels;

	public User(String id) {
		this.id = id;
		watchedParcels = new HashSet<String>();
	}
	
	public String getId() {
		return id;
	}

	public void watch(String parcelId) {
		watchedParcels.add(parcelId);
	}
	
	public Collection<String> getWatchedParcelIds() {
		return watchedParcels;
	}
	
	public boolean isWatching(String parcelId) {
		return watchedParcels.contains(parcelId);
	}

}
