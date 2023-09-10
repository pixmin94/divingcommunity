package com.dive.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dive.backend.model.Account;
import com.dive.backend.model.User;
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
            System.out.println(e.getMessage());
            return "unable to create account";
        }
    }

    public String login(User user) {
        if (repo.login(user) == true) {
            return user.getUsername();
        } else {
            return "unable to login";
        }
        // try {
        //     repo.login(user);
        //     return user.getUsername();
        // } catch (Exception e) {
        //     System.out.println(e.getMessage());
        //     return "unable to login";
        // }
    }
}
