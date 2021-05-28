package ua.pt.simulator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.pt.simulator.entities.House;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {
}