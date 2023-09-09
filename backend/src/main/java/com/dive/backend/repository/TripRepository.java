package com.dive.backend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.dive.backend.model.Trip;

import static com.dive.backend.repository.Queries.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
                return ps;
            }
        };

        jdbcTemplate.update(psc, keyholder);

        Integer createdTripId = keyholder.getKey().intValue();
        return createdTripId;
    }

    public List<Trip> getTrips() {
        return jdbcTemplate.query(SQL_GET_ALL_TRIPS, new BeanPropertyRowMapper<>(Trip.class));
    }

    public boolean updateTrip(Trip trip) {
        return jdbcTemplate.update(SQL_EDIT_TRIP,
                trip.getTitle(),
                trip.getLocation(),
                trip.getStartDate(),
                trip.getEndDate(),
                trip.getTripId()) > 0;
    }
}
