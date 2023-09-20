package com.github.savely03.bookingapp.dto;

import lombok.Builder;

@Builder
public record HotelWithFullInfoByRoomsDto(Long id,
                                          String hotelName,
                                          String city,
                                          Long freeRooms,
                                          Long busyRooms,
                                          Long totalRooms) {
}
