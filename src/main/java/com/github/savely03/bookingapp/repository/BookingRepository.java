package com.github.savely03.bookingapp.repository;

import com.github.savely03.bookingapp.dto.BookingReadDto;
import com.github.savely03.bookingapp.entity.Booking;
import com.github.savely03.bookingapp.mapper.BookingReadDtoRowMapper;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {
    String FIND_ALL = """
            SELECT b.id, room_id, user_id, date_from, date_to, username, full_name, password,
                   role, email, hotel_id, room_number, room_floor, hotel_name, stars, city
            FROM booking b
            JOIN users u on u.id = b.user_id
            JOIN room r on r.id = b.room_id
            JOIN hotel h on r.hotel_id = h.id
            """;
    String FIND_BY_ID = FIND_ALL + " WHERE b.id = :id";

    String FIND_ALL_BY_USER_ID = FIND_ALL + " WHERE user_id = :userId ORDER BY date_from DESC";

    @Query(value = FIND_ALL, rowMapperClass = BookingReadDtoRowMapper.class)
    Iterable<BookingReadDto> findAllWithJoins();

    @Query(value = FIND_BY_ID, rowMapperClass = BookingReadDtoRowMapper.class)
    Optional<BookingReadDto> findByIdWithJoins(@Param("id") Long id);

    @Query(value = FIND_ALL_BY_USER_ID, rowMapperClass = BookingReadDtoRowMapper.class)
    Iterable<BookingReadDto> findAllByUserId(Long userId);

    boolean existsByIdAndDateFromIsAfter(Long id, LocalDate localDate);
}
