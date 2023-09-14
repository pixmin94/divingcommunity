package com.dive.backend.model;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trip {
    private String tripId;
    private String title;
    private String location;
    private Date startDate;
    private Date endDate;
    private String image;
    private List<String> attendees;
}