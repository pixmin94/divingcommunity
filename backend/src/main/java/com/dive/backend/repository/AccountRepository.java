package com.dive.backend.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.dive.backend.model.Account;
import static com.dive.backend.repository.Queries.*;

@Repository
public class AccountRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean createAccount(Account account) {
        // String id = UUID.randomUUID().toString().substring(0, 8);
        // (username, full_name, email, nationality, image)
        return jdbcTemplate.update(SQL_INSERT_ACCOUNT, 
                account.getUsername(),
                account.getFullName(),
                account.getEmail(),
                account.getNationality(),
                account.getImage()) > 0;
    }

}
