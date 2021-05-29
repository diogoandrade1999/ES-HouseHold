package ua.pt.humidity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.pt.humidity.entities.Humidity;

@Repository
public interface HumidityRepository extends JpaRepository<Humidity, Long> {

    Humidity findFirstByHouseIdAndRoomIdOrderByDateDesc(long houseId, long roomId);
}