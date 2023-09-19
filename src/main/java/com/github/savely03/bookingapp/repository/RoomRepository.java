package com.github.savely03.bookingapp.repository;

import com.github.savely03.bookingapp.entity.Room;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {

    @Query("""
            SELECT id, hotel_id, room_number, room_floor
            FROM room
            WHERE hotel_id = :hotel_id
            AND id NOT IN (
                SELECT room_id
                FROM booking
                WHERE date_from BETWEEN :date_from AND :date_to
                OR date_to BETWEEN :date_from AND :date_to
            )
            LIMIT 1
            """)
    Optional<Room> findFreeRoomByHotelAndDate(@Param("hotel_id") Long hotelId,
                                              @Param("date_from") LocalDate dateFrom,
                                              @Param("date_to") LocalDate dateTo);

    @Query("""
            SELECT count(1) > 0 as exists
            FROM room
            WHERE id
                NOT IN (SELECT room_id
                        FROM booking
                        WHERE date_from BETWEEN :date_from AND :date_to
                           OR date_to BETWEEN :date_from AND :date_to)
              AND hotel_id = :hotel_id
            """)
    boolean existsFreeRoomByHotelAndDate(@Param("hotel_id") Long hotelId,
                                         @Param("date_from") LocalDate dateFrom,
                                         @Param("date_to") LocalDate dateTo);

    Optional<Room> findByHotelIdAndRoomNumber(Long hotelId, Short roomNumber);
}