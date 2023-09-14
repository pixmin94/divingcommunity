package com.dive.backend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.dive.backend.model.Account;
import com.dive.backend.model.Trip;

import static com.dive.backend.repository.Queries.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TripRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Integer insertTrip(final Trip trip) {
        KeyHolder keyholder = new GeneratedKeyHolder();
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(SQL_INSERT_TRIP, new String[] { "id" });
                // (title, location, start_date, end_date)
                    ps.setString(1, trip.getTitle());
                    ps.setString(2, trip.getLocation());
                    ps.setDate(3, trip.getStartDate());
                    ps.setDate(4, trip.getEndDate());
                    ps.setString(5, trip.getImage());
                return ps;
            }
        };

        jdbcTemplate.update(psc, keyholder);

        Integer createdTripId = keyholder.getKey().intValue();
        return createdTripId;
    }

    public List<Trip> getTrips() {
        List<Trip> trips = jdbcTemplate.query(SQL_GET_ALL_TRIPS, new BeanPropertyRowMapper<>(Trip.class));

        for (Trip trip : trips) {
            List<String> attendees = jdbcTemplate.queryForList(SQL_GET_TRIP_ATTENDEES, String.class, trip.getTripId());
            trip.setAttendees(attendees);
        }

        return trips;
    }

    public List<Trip> getMyTrips(String username) {
        List<Integer> listOfTripId = jdbcTemplate.queryForList(SQL_GET_MY_TRIPS_ATTENDEES, Integer.class, username);
        List<Trip> myTrips = new ArrayList<>();
        for (Integer tripId : listOfTripId) {
            Trip trip = jdbcTemplate.queryForObject(SQL_GET_MY_TRIPS, BeanPropertyRowMapper.newInstance(Trip.class), tripId);
            myTrips.add(trip);
        }
        // System.out.println(myTrips);
        return myTrips;
    }

    public boolean updateTrip(Trip trip) {
        return jdbcTemplate.update(SQL_EDIT_TRIP,
                trip.getTitle(),
                trip.getLocation(),
                trip.getStartDate(),
                trip.getEndDate(),
                trip.getTripId()) > 0;
    }

    public String joinTrip(String tripId, String username) {
        try {
            jdbcTemplate.queryForObject(SQL_CHECK_ATTENDEE, Integer.class, tripId, username);
            return "You have already joined this trip!";
        } catch (EmptyResultDataAccessException e){
            jdbcTemplate.update(SQL_ADD_ATTENDEES, tripId, username);
                String res = (String) jdbcTemplate.queryForObject(SQL_GET_TRIP_TITLE, String.class, tripId);//BeanPropertyRowMapper.newInstance(String.class), tripId);
            // System.out.println("repo71:"+res);
                return "Joined trip: "+res;
        } catch (Exception e){  
            System.out.println("Error: "+e.getMessage());
            return "An error occured";
        }
    }

    public String leaveTrip(String tripId, String username) {
        try {
            jdbcTemplate.update(SQL_LEAVE_TRIP, tripId, username);
            return "Left trip!";
        } catch (Exception e) {
            return "Error leaving trip";
        }   
    }
    
}
