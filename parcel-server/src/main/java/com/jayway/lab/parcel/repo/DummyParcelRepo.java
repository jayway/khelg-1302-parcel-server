package com.jayway.lab.parcel.repo;

import java.util.ArrayList;
import java.util.List;

import com.jayway.lab.parcel.model.Parcel;

public class DummyParcelRepo implements ParcelRepo {
	
	private List<Parcel> parcels;
	
	public DummyParcelRepo() {
		generateDummyData();
	}
	
	@Override
	public List<Parcel> getAllParcels() {
		return parcels;
	}
	
	@Override
	public Parcel getParcelById(String id) {
		for(Parcel parcel: parcels) {
			if (id.equals(parcel.getId()))
				return parcel;
		}
		return null;
	}

	private void generateDummyData() {
		parcels = new ArrayList<Parcel>();
		parcels.add(new Parcel("001", "Albin", "Bertil", 4));
		parcels.add(new Parcel("002", "Cecilia", "David", 1));
		parcels.add(new Parcel("003", "Erika", "Fredrik", 19));
	}

}
