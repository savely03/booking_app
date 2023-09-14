package com.github.savely03.bookingapp.mapper;

import com.github.savely03.bookingapp.dto.BookingDto;
import com.github.savely03.bookingapp.entity.Booking;
import org.mapstruct.Mapper;

@Mapper
public abstract class BookingMapper implements BaseMapper<Booking, BookingDto> {
}
