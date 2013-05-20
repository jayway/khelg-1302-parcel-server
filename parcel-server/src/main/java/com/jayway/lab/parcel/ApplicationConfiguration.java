package com.jayway.lab.parcel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.jayway.lab.parcel.controller.ParcelController;
import com.jayway.lab.parcel.gcm.GcmService;
import com.jayway.lab.parcel.repo.DummyParcelRepo;
import com.jayway.lab.parcel.repo.ParcelRepo;

/**
 * This class tells spring how to get the different beans that are used in this
 * application. By using the {@link EnableWebMvc} annotation, the url
 * annotations in the controllers will work.
 */
@Configuration
@EnableWebMvc
public class ApplicationConfiguration {

	@Bean
	public ParcelController getParcelController() {
		return new ParcelController();
	}

	@Bean
	public ParcelRepo getParcelRepo() {
		return new DummyParcelRepo();
	}

	@Bean
	public GcmService getGcmService() {
		return new GcmService();
	}

}
