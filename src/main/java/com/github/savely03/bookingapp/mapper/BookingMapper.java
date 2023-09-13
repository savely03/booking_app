package com.github.savely03.bookingapp.mapper;

import com.github.savely03.bookingapp.dto.BookingDto;
import com.github.savely03.bookingapp.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public abstract class BookingMapper implements BaseMapper<Booking, BookingDto> {
    @Override
    @Mapping(target = "room.id", source = "roomId")
    @Mapping(target = "user.id", source = "userId")
    public abstract Booking toEntity(BookingDto dto);

    @Override
    @Mapping(target = "roomId", source = "room.id")
    @Mapping(target = "userId", source = "user.id")
    public abstract BookingDto toDto(Booking entity);
}
