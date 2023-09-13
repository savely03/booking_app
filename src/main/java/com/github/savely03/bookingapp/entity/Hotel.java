package com.github.savely03.bookingapp.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Hotel {
    private Long id;
    private String hotelName;
    private Short stars;
    private String country;
    private String city;
}
