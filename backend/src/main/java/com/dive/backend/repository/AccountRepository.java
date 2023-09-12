package com.dive.backend.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import com.dive.backend.model.Account;
import com.dive.backend.model.User;

import static com.dive.backend.repository.Queries.*;

@Repository
public class AccountRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean createAccount(Account account) {
        // String id = UUID.randomUUID().toString().substring(0, 8);
        String hashedPassword = BCrypt.hashpw(account.getPassword(), BCrypt.gensalt());
        // (username, password, full_name, email, nationality, image)
        return jdbcTemplate.update(SQL_INSERT_ACCOUNT, 
                account.getUsername(),
                hashedPassword,
                account.getFullName(),
                account.getEmail(),
                account.getNationality()) > 0;
    }

    public boolean login(User user) {
        Map<String, Object> userMap = jdbcTemplate.queryForMap(SQL_GET_USER_PASSWORD, user.getUsername());
        System.out.println(userMap);

        if (userMap.get("password") == null) {
            return false;
        }

        boolean res = BCrypt.checkpw(user.getPassword(), userMap.get("password").toString());
        System.out.println(res);
        return res;
    }

    public Account findUserDetails(String username) {
        Account account = new Account();
        account = jdbcTemplate.queryForObject(SQL_GET_USER_DETAILS, BeanPropertyRowMapper.newInstance(Account.class), username);
        return account;
    }

    public boolean updateAccount(Account account) {
        return jdbcTemplate.update(SQL_EDIT_ACCOUNT, 
            account.getPassword(),
            account.getFullName(),
            account.getEmail(),
            account.getNationality()) > 0;
    }

}
