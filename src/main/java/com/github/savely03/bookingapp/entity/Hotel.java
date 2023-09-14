package com.github.savely03.bookingapp.entity;

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
public class Hotel {
    @Id
    private Long id;
    @Column("hotel_name")
    private String hotelName;
    private Short stars;
    private String city;
}
