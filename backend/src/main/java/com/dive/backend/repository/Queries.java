package com.dive.backend.repository;

public class Queries {
    // Trip Queries
    public static final String SQL_INSERT_TRIP = "insert into trips (title, location, start_date, end_date, image) values (?,?,?,?,?)";
    public static final String SQL_GET_ALL_TRIPS = "select * from trips order by trip_id desc";
    public static final String SQL_GET_MY_TRIPS_ATTENDEES = "select trip_id from attendees where username = ?";
    public static final String SQL_GET_MY_TRIPS = "select * from trips where trip_id = ?";
    public static final String SQL_EDIT_TRIP = "update trips set title = ?, location = ?, start_date = ?, end_date = ? where trip_id = ?";

    // Account Queries
    public static final String SQL_INSERT_ACCOUNT = "insert into accounts (username, password, full_name, email, nationality) values (?,?,?,?,?)";
    public static final String SQL_GET_USER_PASSWORD = "select username, password from accounts where username = ?";
    public static final String SQL_GET_USER_DETAILS = "select full_name, email, nationality from accounts where username = ?";
    public static final String SQL_EDIT_ACCOUNT = "update accounts set password = ?, full_name = ?, email = ?, nationality = ? where username = ?";
    public static final String SQL_EDIT_ACCOUNT_WITHOUT_PASSWORD = "update accounts set full_name = ?, email = ?, nationality = ? where username = ?";

    // User Queries
    public static final String SQL_GET_USER_BY_USERNAME = "select * from users where id = ?";

    // Attendees Queries
    public static final String SQL_ADD_ATTENDEES = "insert into attendees (trip_id, username) values (?, ?)";
    public static final String SQL_LEAVE_TRIP = "delete from attendees where trip_id = ? and username = ? ";
    public static final String SQL_GET_TRIP_ATTENDEES = "select username from attendees where trip_id = ?";
    public static final String SQL_CHECK_ATTENDEE = "select 1 from attendees where trip_id = ? and username = ?";
    public static final String SQL_GET_TRIP_TITLE = "select title from trips where trip_id = ?";

}
