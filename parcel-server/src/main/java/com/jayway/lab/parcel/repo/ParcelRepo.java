package com.jayway.lab.parcel.repo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jayway.lab.parcel.model.Parcel;

@Repository
public interface ParcelRepo {

	public abstract List<Parcel> getAllParcels();

	public abstract Parcel getParcelById(String id);

}