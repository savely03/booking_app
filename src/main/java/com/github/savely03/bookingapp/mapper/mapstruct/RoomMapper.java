package com.github.savely03.bookingapp.mapper.mapstruct;

import com.github.savely03.bookingapp.dto.RoomDto;
import com.github.savely03.bookingapp.entity.Room;
import org.mapstruct.Mapper;

@Mapper
public abstract class RoomMapper implements BaseMapper<RoomDto, Room> {
}
