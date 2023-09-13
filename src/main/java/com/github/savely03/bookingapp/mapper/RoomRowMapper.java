package com.github.savely03.bookingapp.mapper;

import com.github.savely03.bookingapp.entity.Room;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RoomRowMapper implements RowMapper<Room> {
    @Override
    public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Room.builder()
                .id(rs.getLong("id"))
                .hotelId(rs.getLong("hotel_id"))
                .roomNumber(rs.getShort("room_number"))
                .roomFloor(rs.getShort("room_floor"))
                .build();
    }
}
