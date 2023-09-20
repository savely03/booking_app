package com.github.savely03.bookingapp.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("booking")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Booking {
    @Id
    private Long id;
    @Column("room_id")
    private Long roomId;
    @Column("user_id")
    private Long userId;
    @Column("date_from")
    private LocalDate dateFrom;
    @Column("date_to")
    private LocalDate dateTo;
}
