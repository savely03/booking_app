package com.github.savely03.bookingapp.mapper;

import com.github.savely03.bookingapp.dto.BookingDto;
import com.github.savely03.bookingapp.entity.Role;
import com.github.savely03.bookingapp.entity.Room;
import com.github.savely03.bookingapp.entity.Users;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BookingDtoRowMapper implements RowMapper<BookingDto> {
    @Override
    public BookingDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        Room room = Room.builder()
                .id(rs.getLong("room_id"))
                .hotelId(rs.getLong("hotel_id"))
                .roomNumber(rs.getShort("room_number"))
                .roomFloor(rs.getShort("room_floor"))
                .build();
        Users user = Users.builder()
                .id(rs.getLong("user_id"))
                .username(rs.getString("username"))
                .fullName(rs.getString("full_name"))
                .role(Role.valueOf(rs.getString("role")))
                .email(rs.getString("email"))
                .build();
        return BookingDto.builder()
                .id(rs.getLong("id"))
                .roomId(room.getId())
                .userId(user.getId())
                .room(room)
                .user(user)
                .dateFrom(rs.getDate("date_from").toLocalDate())
                .dateTo(rs.getDate("date_to").toLocalDate())
                .build();
    }
}
