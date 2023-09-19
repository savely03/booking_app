package com.github.savely03.bookingapp.entity;

import com.github.savely03.bookingapp.validation.HotelConstraint;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("hotel")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@HotelConstraint
public class Hotel { // Dto на создание/изменение не стал делать, так как полностью совпадает с сущностью
    @Id
    private Long id;
    @NotBlank(message = "Название отеля не должно быть пустым")
    @Column("hotel_name")
    private String hotelName;
    @NotNull(message = "Звезды не должны быть пустыми")
    @Min(value = 1, message = "Минимальное кол-во звезд - 1")
    @Max(value = 5, message = "Максимальное кол-во звезд - 5")
    private Short stars;
    @NotBlank(message = "Город не должен быть пустым")
    private String city;
}
