package ua.pt.temperature.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.pt.temperature.entities.Temperature;

@Repository
public interface TemperatureRepository extends JpaRepository<Temperature, Long> {

    Iterable<Temperature> findByDateBetween(Date startDate, Date endDate);

    Iterable<Temperature> findByDateBetweenAndHouseId(Date startDate, Date endDate, long houseId);

    Iterable<Temperature> findByDateBetweenAndHouseIdAndRoomId(Date startDate, Date endDate, long houseId, long roomId);

    Temperature findFirstByHouseIdAndRoomIdOrderByDateDesc(long houseId, long roomId);

}