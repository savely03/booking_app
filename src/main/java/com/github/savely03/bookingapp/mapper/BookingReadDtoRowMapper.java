package com.github.savely03.bookingapp.mapper;

import com.github.savely03.bookingapp.dto.BookingReadDto;
import com.github.savely03.bookingapp.entity.Hotel;
import com.github.savely03.bookingapp.entity.Role;
import com.github.savely03.bookingapp.entity.Room;
import com.github.savely03.bookingapp.entity.Users;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BookingReadDtoRowMapper implements RowMapper<BookingReadDto> {
    @Override
    public BookingReadDto mapRow(ResultSet rs, int rowNum) throws SQLException {
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
        Hotel hotel = Hotel.builder()
                .id(rs.getLong("hotel_id"))
                .hotelName(rs.getString("hotel_name"))
                .stars(rs.getShort("stars"))
                .city(rs.getString("city"))
                .build();
        return BookingReadDto.builder()
                .id(rs.getLong("id"))
                .room(room)
                .user(user)
                .hotel(hotel)
                .dateFrom(rs.getDate("date_from").toLocalDate())
                .dateTo(rs.getDate("date_to").toLocalDate())
                .build();
    }
}
