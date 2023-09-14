package com.github.savely03.bookingapp.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("room")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Room {
    @Id
    private Long id;
    @Column("hotel_id")
    private Long hotelId;
    @Column("room_number")
    private Short roomNumber;
    @Column("room_floor")
    private Short roomFloor;
}
