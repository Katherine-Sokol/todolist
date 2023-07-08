package com.goit.service;

import com.goit.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        String sql = "SELECT username, password FROM \"user\" WHERE username = :username";
        return jdbcTemplate.queryForObject(
                sql,
                Map.of("username", username),
                (rs, rowNum) -> {
                    String name = rs.getString("username");
                    String password = rs.getString("password");
                    return new User(name, password);
                }
        );
    }
}
