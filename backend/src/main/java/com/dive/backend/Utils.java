package com.dive.backend;

// import org.bson.Document;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

import java.io.StringReader;
import java.sql.Date;

import com.dive.backend.model.Account;
import com.dive.backend.model.Trip;
import com.dive.backend.model.User;

public class Utils {
    public static JsonObject toJSON(String json) {
        JsonReader r = (JsonReader) Json.createReader(new StringReader(json));
        return r.readObject();
    }

    public static Trip toTripObject(String trip) {
        JsonObject o = toJSON(trip);
        Trip t = new Trip();
        t.setTitle(o.getString("title"));
        t.setLocation(o.getString("location"));
        t.setStartDate(Date.valueOf(o.getString("startDate")));
        t.setEndDate(Date.valueOf(o.getString("endDate")));
        t.setImage(o.getString("image"));
        return t;
    }

    public static Account toAccountObject(String account) {
        JsonObject o = toJSON(account);
        Account a = new Account();
        // username, full_name, email, nationality, image
        a.setUsername(o.getString("username"));
        a.setPassword(o.getString("password"));
        a.setFullName(o.getString("fullName"));
        a.setEmail(o.getString("email"));
        a.setNationality(o.getString("nationality"));
        // a.setImage(o.getString("image"));
        return a;
    }

    public static User toUserObject(String user) {
        JsonObject o = toJSON(user);
        User u = new User();
        u.setUsername(o.getString("username"));
        u.setPassword(o.getString("password"));
        return u;
    }

    // public static Trip toTripObject(Document doc){
    //     Trip t = new Trip();
    //     t.setTitle(doc.getString("title"));
    //     t.setLocation(doc.getString("location"));
    //     t.setStartDate(Date.valueOf(doc.getString("startDate")));
    //     t.setEndDate(Date.valueOf(doc.getString("endDate")));
    //     return t;
    // }
}
