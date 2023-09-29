package com.github.savely03.bookingapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("room")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
