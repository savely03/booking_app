package com.github.savely03.bookingapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.savely03.bookingapp.entity.Room;
import com.github.savely03.bookingapp.entity.Users;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BookingDto {
    private Long id;
    private Long roomId;
    private Room room;
    private Long userId;
    private Users user;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate dateFrom;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate dateTo;
}
