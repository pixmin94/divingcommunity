package com.dive.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dive.backend.Utils;
import com.dive.backend.model.Account;
import com.dive.backend.model.Trip;
import com.dive.backend.model.User;
import com.dive.backend.service.AccountService;

@RestController
@RequestMapping(path="/api")
@CrossOrigin
public class AccountController {
    @Autowired
    private AccountService service;

    @PostMapping(path="/createaccount")
    private ResponseEntity<String> createAccount(@RequestBody String account) {
        // System.out.println("Creating account for: "+account);
        Account a = Utils.toAccountObject(account);
        String res = service.createAccount(a);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(res);
    }

    @PostMapping(path="/login", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<String> login(@RequestBody User user) {
        // System.out.println("Logging in :"+user);
        // User u = Utils.toUserObject(user);
        String res = service.login(user);
        ResponseCookie cookie = ResponseCookie.from("username", res)
                .maxAge(3600)
                .path("/")
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.SET_COOKIE, cookie.toString());
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers)
                .body(res);
    }

    @PostMapping(path="/updateaccount")
    private ResponseEntity<Boolean> updateAccount(@RequestBody Account account) {
        // System.out.println("Editing account for: "+account);
        boolean res = service.updateAccount(account);
        return ResponseEntity
            .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(res);
    }

    @GetMapping(path="/finduser")
    private ResponseEntity<Account> findUserDetails(@RequestParam("username") String username) {
        Account res = service.getUserDetails(username);
        // System.out.println("Finding details for: "+username);
        return ResponseEntity
            .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(res);
    }
}
