package com.github.savely03.bookingapp.sql;

import lombok.experimental.UtilityClass;

@UtilityClass
public class BookingQuery {

    public static final String INSERT = """
            INSERT INTO booking (room_id, user_id, date_from, date_to)
            VALUES (:room_id, :user_id, :date_from, :date_to)
            RETURNING id
            """;

    public static final String FIND_ALL = """
            SELECT b.id,
                   room_id,
                   user_id,
                   date_from,
                   date_to,
                   hotel_id,
                   room_number,
                   room_floor,
                   username,
                   full_name,
                   role,
                   email
            FROM booking b
            JOIN room r ON r.id = b.room_id
            JOIN users u on u.id = b.user_id
            """;

    public static final String FIND_BY_ID = FIND_ALL + " WHERE id = :id";

    public static final String EXISTS = """
            SELECT 1
            FROM booking
            WHERE id = :id
            """;
}
