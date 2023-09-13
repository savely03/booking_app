package com.github.savely03.bookingapp.repository;

import com.github.savely03.bookingapp.entity.Booking;
import com.github.savely03.bookingapp.mapper.BookingRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import static com.github.savely03.bookingapp.sql.BookingQuery.*;

@Repository
@RequiredArgsConstructor
public class BookingRepository implements CrudRepository<Long, Booking> {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final BookingRowMapper bookingRowMapper;

    @Override
    public Booking create(Booking booking) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("room_id", booking.getRoom().getId())
                .addValue("user_id", booking.getUser().getId())
                .addValue("date_from", booking.getDateFrom())
                .addValue("date_to", booking.getDateTo());

        Long id = jdbcTemplate.queryForObject(INSERT, parameterSource, Long.class);
        return findById(id).orElse(null);
    }

    @Override
    public Optional<Booking> findById(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource().addValue("id", id);
        List<Booking> mayBeBooking = jdbcTemplate.query(FIND_BY_ID, parameterSource, bookingRowMapper);
        Booking booking = mayBeBooking.isEmpty() ? null : mayBeBooking.get(0);
        return Optional.ofNullable(booking);
    }

    @Override
    public List<Booking> findAll() {
        return jdbcTemplate.query(FIND_ALL, bookingRowMapper);
    }

    @Override
    public Boolean exists(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource().addValue("id", id);
        return jdbcTemplate.query(EXISTS, parameterSource, ResultSet::next);
    }
}
