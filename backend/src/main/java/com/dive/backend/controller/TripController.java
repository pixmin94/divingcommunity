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
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping(path="/getmytrips")
    private ResponseEntity<List<Trip>> getMyTrips(@RequestParam("username") String username) {
        List<Trip> res = service.getMyTrips(username);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(res);
    }

    @PostMapping(path="/updatetrip")
    private ResponseEntity<Integer> updateTrip(@RequestBody String trip) {
        Trip t = Utils.toTripObject(trip);
        // System.out.println("Trip object: " + t);
        int res = service.updateTrip(t);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(res);
    }

    @GetMapping(path="/jointrip" , produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<String> joinTrip(@RequestParam("tripId") String tripId,
        @RequestParam("username") String username) {
            System.out.println(tripId+username);
            String res = service.joinTrip(tripId, username);
            // System.out.println("controller64:"+res);
            return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(res);
        }
    
    @GetMapping(path="/leavetrip")
    private ResponseEntity<String> leaveTrip(@RequestParam("tripId") String tripId,
        @RequestParam("username") String username) {
            String res = service.leaveTrip(tripId, username);
            return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(res);
        }
}
