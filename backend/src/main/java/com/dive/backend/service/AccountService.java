package com.dive.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dive.backend.model.Account;
import com.dive.backend.repository.AccountRepository;

@Service
public class AccountService {
    @Autowired
    private AccountRepository repo;

    public String createAccount(Account account){
        try {
            repo.createAccount(account);
            return account.getUsername();
        } catch (Exception e){
            return e.toString();
        }
    }
}
