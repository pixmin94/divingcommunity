package com.dive.backend.repository;

public class Queries {
    // Trip Queries
    public static final String SQL_INSERT_TRIP = "insert into trips (title, location, start_date, end_date) values (?,?,?,?)";
    public static final String SQL_GET_ALL_TRIPS = "select * from trips order by trip_id desc";
    public static final String SQL_EDIT_TRIP = "update trips set title = ?, location = ?, start_date = ?, end_date = ? where trip_id = ?";

    // Account Queries
    public static final String SQL_INSERT_ACCOUNT = "insert into accounts (username, password, full_name, email, nationality) values (?,?,?,?,?)";
    public static final String SQL_GET_USER_PASSWORD = "select username, password from accounts where username = ?";

    // User Queries
    public static final String SQL_GET_USER_BY_USERNAME = "select * from users where id = ?";
}
