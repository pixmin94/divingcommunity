package com.dive.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dive.backend.Utils;
import com.dive.backend.model.Account;
import com.dive.backend.model.Trip;
import com.dive.backend.service.AccountService;

@RestController
@RequestMapping(path="/api")
@CrossOrigin
public class AccountController {
    @Autowired
    private AccountService service;

    @PostMapping(path="/createaccount")
    private ResponseEntity<String> createAccount(@RequestBody String account) {
        System.out.println(account);
        Account a = Utils.toAccountObject(account);
        String res = service.createAccount(a);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(res);
    }
}
