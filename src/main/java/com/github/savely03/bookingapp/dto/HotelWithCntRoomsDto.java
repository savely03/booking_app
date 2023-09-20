package com.github.savely03.bookingapp.dto;

import lombok.Builder;

@Builder
public record HotelWithCntRoomsDto(
        Long id,
        String hotelName,
        Short stars,
        String city,
        Long cntRooms) {
}
