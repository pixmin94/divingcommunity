package com.dive.backend;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dive.backend.model.Account;
import com.dive.backend.model.Trip;
import com.dive.backend.repository.AccountRepository;
import com.dive.backend.repository.TripRepository;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {

	// @Autowired
	// private TripRepository repo;

	@Autowired
	private AccountRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Trip trip = new Trip();
		// trip.setTitle("New Trip!");
		// trip.setLocation("bali");
		// trip.setStartDate(new Date(System.currentTimeMillis()));
		// trip.setEndDate(Date.valueOf("2023-09-24"));
		// System.out.println(trip);
		// int result = repo.insertTrip(trip);

		Account account = new Account("hello", "Hello World", "hello@gmail.com", "", "Singapore");
		boolean result = repo.createAccount(account);

		System.out.println(">>>>>>>>>>RESULT: "+result);
	}

}
