package com.github.savely03.bookingapp.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Room {
    private Long id;
    private Long hotelId;
    private Short roomNumber;
    private Short roomFloor;
}
