package com.dive.backend.repository;

public class Queries {
    // Trip Queries
    public static final String SQL_INSERT_TRIP = "insert into trips (title, location, start_date, end_date) values (?,?,?,?)";

    // Account Queries
    public static final String SQL_INSERT_ACCOUNT = "insert into accounts (username, full_name, email, nationality, image) values (?,?,?,?,?)";
}
