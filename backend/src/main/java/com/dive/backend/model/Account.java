package com.dive.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private String username;
    private String fullName;
    private String email;
    private String image;
    private String nationality;
}
