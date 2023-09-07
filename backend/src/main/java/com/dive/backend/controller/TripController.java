package com.dive.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dive.backend.Utils;
import com.dive.backend.model.Trip;
import com.dive.backend.service.TripService;

@RestController
@RequestMapping(path="/api")
@CrossOrigin
public class TripController {
    @Autowired
    private TripService service;

    @PostMapping(path="/createtrip")
    private ResponseEntity<Integer> createTrip(@RequestBody String trip) {
        System.out.println(trip);
        Trip t = Utils.toTripObject(trip);
        System.out.println("Trip object: " + t);
        int res = service.createTrip(t);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(res);
    }

    @GetMapping(path ="/gettrips")
    private ResponseEntity<List<Trip>> getAllTrips() {
        List<Trip> res = service.getAllTrips();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(res);
    }
}
