package com.github.savely03.bookingapp.sql;

import lombok.experimental.UtilityClass;

@UtilityClass
public class HotelQuery {
    public static final String INSERT = """
            INSERT INTO hotel (hotel_name, stars, country, city)
            VALUES (:hotel_name, :stars, :country, :city)
            RETURNING id
            """;

    public static final String FIND_ALL = """
            SELECT id,
                   hotel_name,
                   stars,
                   country,
                   city
            FROM hotel
            """;
    public static final String FIND_BY_ID = FIND_ALL + " WHERE id = :id";

    public static final String EXISTS = """
            SELECT 1
            FROM hotel
            WHERE id = :id
            """;

}
