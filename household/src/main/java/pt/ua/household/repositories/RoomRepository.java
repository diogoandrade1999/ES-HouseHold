package pt.ua.household.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pt.ua.household.entities.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

}
