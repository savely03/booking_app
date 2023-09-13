package com.github.savely03.bookingapp.mapper;

import com.github.savely03.bookingapp.entity.Booking;
import com.github.savely03.bookingapp.entity.Role;
import com.github.savely03.bookingapp.entity.Room;
import com.github.savely03.bookingapp.entity.Users;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BookingRowMapper implements RowMapper<Booking> {
    @Override
    public Booking mapRow(ResultSet rs, int rowNum) throws SQLException {
        Room room = Room.builder()
                .id(rs.getLong("room_id"))
                .roomFloor(rs.getShort("room_floor"))
                .roomNumber(rs.getShort("room_number"))
                .hotelId(rs.getLong("hotel_id"))
                .build();
        Users users = Users.builder()
                .id(rs.getLong("user_id"))
                .email(rs.getString("email"))
                .fullName(rs.getString("full_name"))
                .username(rs.getString("username"))
                .role(Role.valueOf(rs.getString("role")))
                .build();
        return Booking.builder()
                .id(rs.getLong("id"))
                .user(users)
                .dateFrom(rs.getDate("date_from").toLocalDate())
                .dateTo(rs.getDate("date_to").toLocalDate())
                .room(room)
                .build();
    }
}
