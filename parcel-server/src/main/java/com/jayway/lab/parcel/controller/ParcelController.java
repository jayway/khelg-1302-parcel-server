package com.jayway.lab.parcel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jayway.lab.parcel.gcm.GcmService;
import com.jayway.lab.parcel.model.Parcel;
import com.jayway.lab.parcel.model.Parcel.Status;
import com.jayway.lab.parcel.repo.ParcelRepo;

@Controller
@RequestMapping("/")
public class ParcelController {

	@Autowired
	private ParcelRepo parcelRepo;

	@Autowired
	private GcmService gcmService;

	@RequestMapping(method = RequestMethod.GET, value = "parcels")
	public @ResponseBody
	List<Parcel> getParcels() {
		return parcelRepo.getAllParcels();
	}

	@RequestMapping(method = RequestMethod.GET, value = "parcels/{id}")
	public @ResponseBody
	Parcel getParcel(@PathVariable String id) {
		return parcelRepo.getParcelById(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/parcels/{id}")
	public @ResponseBody
	void updateParcelPost(@PathVariable String id, @RequestParam String status) {
		updateParcel(id, status);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/parcels/{id}")
	public @ResponseBody
	void updateParcel(@PathVariable String id, @RequestParam String status) {
		Parcel parcel = parcelRepo.getParcelById(id);
		if (parcel == null || status == null) {
			throw new IllegalArgumentException();
		}
		Status newStatus = Status.valueOf(status);
		parcel.setStatus(newStatus);
	}
}
