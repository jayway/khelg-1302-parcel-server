package com.jayway.lab.parcel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jayway.lab.parcel.gcm.GcmService;
import com.jayway.lab.parcel.model.Parcel;
import com.jayway.lab.parcel.repo.ParcelRepo;

@Controller
@RequestMapping("/")
public class ParcelController {
	
	@Autowired
	private ParcelRepo packageRepo;
	
	@Autowired
	private GcmService gcmService;

	@RequestMapping(method=RequestMethod.GET, value="parcels")
	public @ResponseBody List<Parcel> getParcels() {
		return packageRepo.getAllParcels();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="parcels/{id}")
	public @ResponseBody Parcel getParcel(@PathVariable String id) {
		return packageRepo.getParcelById(id);
	}
}
