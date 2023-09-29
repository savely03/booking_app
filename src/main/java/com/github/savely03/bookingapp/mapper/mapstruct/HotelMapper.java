package com.github.savely03.bookingapp.mapper.mapstruct;

import com.github.savely03.bookingapp.dto.HotelDto;
import com.github.savely03.bookingapp.entity.Hotel;
import org.mapstruct.Mapper;

@Mapper
public abstract class HotelMapper implements BaseMapper<HotelDto, Hotel> {
}
