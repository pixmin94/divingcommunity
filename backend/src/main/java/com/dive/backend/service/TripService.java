package com.dive.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dive.backend.model.Trip;
import com.dive.backend.repository.TripRepository;

@Service
public class TripService {
    @Autowired
    private TripRepository repo;

    public Integer createTrip(Trip trip) {
        return repo.insertTrip(trip);
    }

    public List<Trip> getAllTrips() {
        return repo.getTrips();
    }

    public Integer updateTrip(Trip trip) {
        try {
            repo.updateTrip(trip);
            return Integer.valueOf(trip.getTripId());
        } catch (Exception e){
            return 0;
        }
    }
}
