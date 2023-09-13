package com.github.savely03.bookingapp.mapper;

import com.github.savely03.bookingapp.entity.Hotel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class HotelRowMapper implements RowMapper<Hotel> {
    @Override
    public Hotel mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Hotel.builder()
                    .id(rs.getLong("id"))
                    .hotelName(rs.getString("hotel_name"))
                    .stars(rs.getShort("stars"))
                    .country(rs.getString("country"))
                    .city(rs.getString("city"))
                    .build();
    }
}
