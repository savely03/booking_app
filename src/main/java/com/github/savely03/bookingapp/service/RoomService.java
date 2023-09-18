package com.github.savely03.bookingapp.service;

import com.github.savely03.bookingapp.entity.Room;
import com.github.savely03.bookingapp.exception.RoomNotFoundException;
import com.github.savely03.bookingapp.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoomService {

    private final RoomRepository roomRepository;

    @Transactional
    public Room create(Room room) {
        return roomRepository.save(room);
    }

    @Transactional
    public void update(Long id, Room room) {
        if (roomRepository.existsById(id)) {
            room.setId(id);
            roomRepository.save(room);
            return;
        }
        throw new RoomNotFoundException();
    }

    public Room findById(Long id) {
        return roomRepository.findById(id).orElseThrow(RoomNotFoundException::new);
    }
}
