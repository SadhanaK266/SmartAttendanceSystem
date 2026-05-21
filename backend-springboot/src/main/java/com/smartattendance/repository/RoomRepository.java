package com.smartattendance.repository;

import com.smartattendance.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository
        extends JpaRepository<Room, Long> {
}