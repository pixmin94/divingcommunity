package com.dive.backend.service;

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
}
