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

/**
 * <p>
 * The ParcelController implements the API concerning the parcels. The paths
 * leading to the different methods is a combination of the base path to the api
 * and the {@link RequestMapping} annotation of the methods.
 * </p>
 * <p>
 * The base part can be found in web.xml. Check the url-pattern tag of the
 * servlet-mapping.(At the time of writing this comment, it's "/api".)
 * </p>
 * <p>
 * The returned objects are converted to JSON by magic which is performed by
 * spring if the following conditions are fulfilled:
 * </p>
 * <nl>
 * <li>The methods returns an object that is not a String.</li>
 * <li>The methods are annotated with @ResponseBody.</li>
 * <li>Jackson is in the classpath (It's included by maven).</li>
 * <li>The configuration class is annotated with @EnableWebMvc</li>
 * </nl>
 * 
 * 
 */
@Controller
@RequestMapping("/parcels")
public class ParcelController {

	@Autowired
	private ParcelRepo parcelRepo;

	@Autowired
	private GcmService gcmService;

	/**
	 * Retreives all the parcels. This method is reached with the URL:
	 * {baseUrl}/parcels
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "")
	public @ResponseBody
	List<Parcel> getParcels() {
		return parcelRepo.getAllParcels();
	}

	/**
	 * Retrieves the package with the specified id. This
	 * 
	 * @param id
	 *            the id of the parcel to return
	 * @return the parcel with the specified id.
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public @ResponseBody
	Parcel getParcel(@PathVariable String id) {
		return parcelRepo.getParcelById(id);
	}

	/**
	 * Updates the specified parcel with POST. See
	 * {@link #updateParcel(String, String, String, Integer, String)} for more
	 * details.
	 * 
	 * @param id
	 * @param sender
	 * @param receiver
	 * @param weight
	 * @param status
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/{id}")
	public @ResponseBody
	void updateParcelPost(@PathVariable String id,
			@RequestParam(required = false) String sender,
			@RequestParam(required = false) String receiver,
			@RequestParam(required = false) Integer weight,
			@RequestParam(required = false) String status) {
		updateParcel(id, sender, receiver, weight, status);
	}

	/**
	 * Updates the specified package. The URL to reach this method is
	 * {baseUrl}/parcels/{id}. The new values are sent as normal URL-encoded
	 * values in the body (not the actual URL).
	 * 
	 * Only the values that are sent in are updated, so a valid body would be
	 * "sender=Albin&status=SENT". This method is reached by using PUT, but to
	 * simplify web development, it can be reached by POST as well.
	 * 
	 * @see #updateParcelPost(String, String, String, Integer, String)
	 * 
	 * @param id
	 *            the package to update
	 * @param sender
	 *            the new sender of the parcel, or <code>null</code> if it
	 *            should be ignored.
	 * @param receiver
	 *            the new receiver of the parcel, or <code>null</code> if it
	 *            should be ignored.
	 * @param weight
	 *            the new weight of the parcel, or <code>null</code> if it
	 *            should be ignored.
	 * @param status
	 *            the new status of the parcel, or <code>null</code> if it
	 *            should be ignored.
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public @ResponseBody
	void updateParcel(@PathVariable String id,
			@RequestParam(required = false) String sender,
			@RequestParam(required = false) String receiver,
			@RequestParam(required = false) Integer weight,
			@RequestParam(required = false) String status) {
		Parcel parcel = parcelRepo.getParcelById(id);
		if (parcel == null || status == null) {
			throw new IllegalArgumentException();
		}
		if (sender != null)
			parcel.setSender(sender);
		if (receiver != null)
			parcel.setReceiver(receiver);
		if (weight != null)
			parcel.setWeight(weight);
		if (status != null) {
			Status newStatus = Status.valueOf(status);
			parcel.setStatus(newStatus);
			gcmService.notifyStatusChange(parcel);
		}
	}
}
