package pt.ua.simulator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pt.ua.simulator.entities.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}