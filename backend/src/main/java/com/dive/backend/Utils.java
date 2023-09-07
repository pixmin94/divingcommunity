package com.dive.backend;

// import org.bson.Document;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

import java.io.StringReader;
import java.sql.Date;

import com.dive.backend.model.Trip;

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
        return t;
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
