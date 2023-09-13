package com.github.savely03.bookingapp.repository;

import com.github.savely03.bookingapp.entity.Users;
import com.github.savely03.bookingapp.mapper.UserRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import static com.github.savely03.bookingapp.sql.UserQuery.*;

@Repository
@RequiredArgsConstructor
public class UserRepository implements CrudRepository<Long, Users> {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;

    @Override
    public Users create(Users user) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("username", user.getUsername())
                .addValue("full_name", user.getFullName())
                .addValue("password", user.getPassword())
                .addValue("role", user.getRole().name())
                .addValue("email", user.getEmail());

        Long id = jdbcTemplate.queryForObject(INSERT, parameterSource, Long.class);
        user.setId(id);

        return user;
    }

    @Override
    public Optional<Users> findById(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource().addValue("id", id);
        List<Users> mayBeUser = jdbcTemplate.query(FIND_BY_ID, parameterSource, userRowMapper);
        Users user = mayBeUser.isEmpty() ? null : mayBeUser.get(0);
        return Optional.ofNullable(user);
    }

    @Override
    public List<Users> findAll() {
        return jdbcTemplate.query(FIND_ALL, userRowMapper);
    }

    @Override
    public Boolean exists(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource().addValue("id", id);
        return jdbcTemplate.query(EXISTS, parameterSource, ResultSet::next);
    }
}
