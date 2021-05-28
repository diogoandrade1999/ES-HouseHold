package pt.ua.simulator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pt.ua.simulator.entities.House;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {
}