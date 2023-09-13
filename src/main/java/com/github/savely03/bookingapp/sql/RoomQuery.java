package com.github.savely03.bookingapp.sql;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RoomQuery {
    public static final String INSERT = """
            INSERT INTO room (hotel_id, room_number, room_floor)
            VALUES (:hotel_id, :room_number, :room_floor)
            RETURNING id
            """;

    public static final String FIND_ALL = """
            SELECT id,
                   hotel_id,
                   room_number,
                   room_floor
            FROM room
            """;
    public static final String FIND_BY_ID = FIND_ALL + " WHERE id = :id";

    public static final String EXISTS = """
            SELECT 1
            FROM room
            where id = :id
            """;
}
