package com.github.savely03.bookingapp.mapper;

import com.github.savely03.bookingapp.dto.HotelWithCntRoomsDto;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class HotelWithCntRoomsRowMapper implements RowMapper<HotelWithCntRoomsDto> {

    @Override
    public HotelWithCntRoomsDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return HotelWithCntRoomsDto.builder()
                .id(rs.getLong("id"))
                .hotelName(rs.getString("hotel_name"))
                .city(rs.getString("city"))
                .stars(rs.getShort("stars"))
                .cntRooms(rs.getLong("cnt_rooms"))
                .build();
    }
}
