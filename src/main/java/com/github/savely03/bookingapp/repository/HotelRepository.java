package com.github.savely03.bookingapp.repository;

import com.github.savely03.bookingapp.entity.Hotel;
import com.github.savely03.bookingapp.mapper.HotelRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import static com.github.savely03.bookingapp.sql.HotelQuery.*;

@Repository
@RequiredArgsConstructor
public class HotelRepository implements CrudRepository<Long, Hotel> {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final HotelRowMapper hotelRowMapper;

    @Override
    public Hotel create(Hotel hotel) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("hotel_name", hotel.getHotelName())
                .addValue("stars", hotel.getStars())
                .addValue("country", hotel.getCountry())
                .addValue("city", hotel.getCity());

        Long id = jdbcTemplate.queryForObject(INSERT, parameterSource, Long.class);
        hotel.setId(id);

        return hotel;
    }

    @Override
    public Optional<Hotel> findById(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource().addValue("id", id);
        List<Hotel> mayBeHotel = jdbcTemplate.query(FIND_BY_ID, parameterSource, hotelRowMapper);
        Hotel hotel = mayBeHotel.isEmpty() ? null : mayBeHotel.get(0);
        return Optional.ofNullable(hotel);
    }

    @Override
    public List<Hotel> findAll() {
        return jdbcTemplate.query(FIND_ALL, hotelRowMapper);
    }

    @Override
    public Boolean exists(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource().addValue("id", id);
        return jdbcTemplate.query(EXISTS, parameterSource, ResultSet::next);
    }
}
