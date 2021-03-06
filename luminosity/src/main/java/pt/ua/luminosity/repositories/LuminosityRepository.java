package pt.ua.luminosity.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pt.ua.luminosity.entities.Luminosity;

@Repository
public interface LuminosityRepository extends JpaRepository<Luminosity, Long> {

    Iterable<Luminosity> findByDateBetween(Date startDate, Date endDate);

    Iterable<Luminosity> findByDateBetweenAndHouseId(Date startDate, Date endDate, long houseId);

    Iterable<Luminosity> findByDateBetweenAndHouseIdAndRoomId(Date startDate, Date endDate, long houseId, long roomId);

    Luminosity findFirstByHouseIdAndRoomIdOrderByDateDesc(long houseId, long roomId);

}