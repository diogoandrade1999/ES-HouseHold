package pt.ua.humidity.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pt.ua.humidity.entities.Humidity;

@Repository
public interface HumidityRepository extends JpaRepository<Humidity, Long> {

    Iterable<Humidity> findByDateBetween(Date startDate, Date endDate);

    Iterable<Humidity> findByDateBetweenAndHouseId(Date startDate, Date endDate, long houseId);

    Iterable<Humidity> findByDateBetweenAndHouseIdAndRoomId(Date startDate, Date endDate, long houseId, long roomId);

    Humidity findFirstByHouseIdAndRoomIdOrderByDateDesc(long houseId, long roomId);

}