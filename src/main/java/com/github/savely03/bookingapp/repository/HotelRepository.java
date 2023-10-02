package com.github.savely03.bookingapp.repository;

import com.github.savely03.bookingapp.dto.HotelWithCntRoomsDto;
import com.github.savely03.bookingapp.dto.HotelWithFullInfoByRoomsDto;
import com.github.savely03.bookingapp.entity.Hotel;
import com.github.savely03.bookingapp.mapper.HotelWithCntRoomsRowMapper;
import com.github.savely03.bookingapp.mapper.HotelWithFullInfoByRoomsRowMapper;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, Long> {

    String FIND_ALL_WITH_FULL_INFO = """
            SELECT h.id,
                   h.hotel_name,
                   h.city,
                   count(r.id)                    as total_rooms,
                   count(b.room_id)               as busy_rooms,
                   count(r.id) - count(b.room_id) as free_rooms
            FROM hotel h
                      LEFT JOIN room r ON h.id = r.hotel_id
                      LEFT JOIN
                  (SELECT room_id
                   FROM booking
                   WHERE current_date BETWEEN date_from AND date_to) b
                  ON r.id = b.room_id
            GROUP BY h.id, h.hotel_name, h.city
            """;

    String FIND_ALL_WITH_FULL_INFO_BY_CITY_AND_STARS =
            FIND_ALL_WITH_FULL_INFO + " HAVING h.city ILIKE concat(:city, '%') AND h.stars = :stars";

    @Query(value = """
            SELECT id, hotel_name, stars, city, hotel_id, cnt_rooms
                FROM hotel h
                JOIN (
                    SELECT hotel_id, count(1) as cnt_rooms
                    FROM room
                    WHERE id
                    NOT IN (
                        SELECT room_id
                        FROM booking
                        WHERE date_from BETWEEN :date_from AND :date_to
                        OR date_to BETWEEN :date_from AND :date_to)
                        GROUP BY hotel_id
                        ) r
                ON h.id = r.hotel_id
                WHERE stars = :stars
                AND city ILIKE concat(:city, '%')
            """, rowMapperClass = HotelWithCntRoomsRowMapper.class)
    List<HotelWithCntRoomsDto> findAllFreeHotelsByFilter(@Param("date_from") LocalDate dateFrom,
                                                         @Param("date_to") LocalDate dateTo,
                                                         @Param("stars") Short stars,
                                                         @Param("city") String city);

    @Query(value = FIND_ALL_WITH_FULL_INFO, rowMapperClass = HotelWithFullInfoByRoomsRowMapper.class)
    Iterable<HotelWithFullInfoByRoomsDto> findAllWithFullInfoByRooms();

    @Query(value = FIND_ALL_WITH_FULL_INFO_BY_CITY_AND_STARS, rowMapperClass = HotelWithFullInfoByRoomsRowMapper.class)
    Iterable<HotelWithFullInfoByRoomsDto> findAllWithFullInfoByRooms(@Param("city") String city,
                                                                     @Param("stars") Short stars);

    @Query("""
            SELECT id, hotel_name, stars, city
            FROM hotel
            WHERE hotel_name = :hotel_name
            AND city = :city
            """)
    Optional<Hotel> findByHotelNameAndCity(@Param("hotel_name") String hotelName,
                                           @Param("city") String city);
}
