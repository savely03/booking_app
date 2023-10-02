package com.github.savely03.bookingapp.mapper.mapstruct;

import com.github.savely03.bookingapp.dto.UserDto;
import com.github.savely03.bookingapp.entity.Users;
import org.mapstruct.Mapper;

@Mapper
public abstract class UserMapper implements BaseMapper<UserDto, Users> {
}
