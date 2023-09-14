package com.github.savely03.bookingapp.repository;

import com.github.savely03.bookingapp.dto.BookingDto;
import com.github.savely03.bookingapp.entity.Booking;
import com.github.savely03.bookingapp.mapper.BookingDtoRowMapper;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {
    String FIND_ALL = """
            SELECT b.id, room_id, user_id, date_from, date_to, username,
                   full_name, password, role, email, hotel_id, room_number, room_floor
            FROM booking b
            JOIN users u on u.id = b.user_id
            join room r on r.id = b.room_id
            """;

    String FIND_BY_ID = FIND_ALL + " WHERE b.id = :id";

    @Query(value = FIND_ALL, rowMapperClass = BookingDtoRowMapper.class)
    Iterable<BookingDto> findAllWithJoins();

    @Query(value = FIND_BY_ID, rowMapperClass = BookingDtoRowMapper.class)
    Optional<BookingDto> findByIdWithJoins(@Param("id") Long id);
}
