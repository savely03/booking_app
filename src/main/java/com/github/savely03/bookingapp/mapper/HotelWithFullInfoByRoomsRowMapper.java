package com.github.savely03.bookingapp.mapper;

import com.github.savely03.bookingapp.dto.HotelWithFullInfoByRoomsDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HotelWithFullInfoByRoomsRowMapper implements RowMapper<HotelWithFullInfoByRoomsDto> {

    @Override
    public HotelWithFullInfoByRoomsDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return HotelWithFullInfoByRoomsDto.builder()
                .id(rs.getLong("id"))
                .hotelName(rs.getString("hotel_name"))
                .city(rs.getString("city"))
                .freeRooms(rs.getLong("free_rooms"))
                .busyRooms(rs.getLong("busy_rooms"))
                .totalRooms(rs.getLong("total_rooms"))
                .build();
    }
}
