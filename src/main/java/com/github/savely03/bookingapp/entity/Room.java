package com.github.savely03.bookingapp.entity;

import com.github.savely03.bookingapp.validation.RoomConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("room")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RoomConstraint
public class Room { // Dto на создание/изменение не стал делать, так как полностью совпадает с сущностью
    @Id
    private Long id;
    @NotNull(message = "Отель не должен быть пустым")
    @Column("hotel_id")
    private Long hotelId;
    @NotNull(message = "Номер не должен быть пустым")
    @Column("room_number")
    private Short roomNumber;
    @NotNull(message = "Этаж не должен быть пустым")
    @Column("room_floor")
    private Short roomFloor;
}
