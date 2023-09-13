package com.github.savely03.bookingapp.repository;

import com.github.savely03.bookingapp.entity.Room;
import com.github.savely03.bookingapp.mapper.RoomRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import static com.github.savely03.bookingapp.sql.RoomQuery.*;

@Repository
@RequiredArgsConstructor
public class RoomRepository implements CrudRepository<Long, Room> {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RoomRowMapper roomRowMapper;

    @Override
    public Room create(Room room) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("hotel_id", room.getHotelId())
                .addValue("room_number", room.getRoomNumber())
                .addValue("room_floor", room.getRoomFloor());

        Long id = jdbcTemplate.queryForObject(INSERT, parameterSource, Long.class);
        room.setId(id);

        return room;
    }

    @Override
    public Optional<Room> findById(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource().addValue("id", id);
        List<Room> mayBeRoom = jdbcTemplate.query(FIND_BY_ID, parameterSource, roomRowMapper);
        Room room = mayBeRoom.isEmpty() ? null : mayBeRoom.get(0);
        return Optional.ofNullable(room);
    }

    @Override
    public List<Room> findAll() {
        return jdbcTemplate.query(FIND_ALL, roomRowMapper);
    }

    @Override
    public Boolean exists(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource().addValue("id", id);
        return jdbcTemplate.query(EXISTS, parameterSource, ResultSet::next);
    }
}
