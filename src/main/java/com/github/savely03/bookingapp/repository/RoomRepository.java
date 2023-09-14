package com.github.savely03.bookingapp.repository;

import com.github.savely03.bookingapp.entity.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
}
