package com.github.savely03.bookingapp.repository;

import com.github.savely03.bookingapp.dto.HotelWithCntRoomsDto;
import com.github.savely03.bookingapp.entity.Hotel;
import com.github.savely03.bookingapp.mapper.HotelWithCntRoomsRowMapper;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, Long> {

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
                AND city = :city
            """, rowMapperClass = HotelWithCntRoomsRowMapper.class)
    List<HotelWithCntRoomsDto> findAllFree(@Param("date_from") LocalDate dateFrom,
                                           @Param("date_to") LocalDate dateTo,
                                           @Param("stars") Short stars,
                                           @Param("city") String city);

}
