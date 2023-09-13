package com.github.savely03.bookingapp.mapper;

import com.github.savely03.bookingapp.entity.Role;
import com.github.savely03.bookingapp.entity.Users;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserRowMapper implements RowMapper<Users> {


    @Override
    public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Users.builder()
                .id(rs.getLong("id"))
                .username(rs.getString("username"))
                .fullName(rs.getString("full_name"))
                .role(Role.valueOf(rs.getString("role")))
                .email(rs.getString("email"))
                .build();
    }
}
