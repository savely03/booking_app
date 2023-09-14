package com.github.savely03.bookingapp.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class HotelWithCntRoomsDto {
    private Long id;
    private String hotelName;
    private Short stars;
    private String city;
    private Long cntRooms;
}
