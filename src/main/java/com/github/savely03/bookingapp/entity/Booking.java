package com.github.savely03.bookingapp.entity;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Booking {
    private Long id;
    private Room room;
    private Users user;
    private LocalDate dateFrom;
    private LocalDate dateTo;
}
