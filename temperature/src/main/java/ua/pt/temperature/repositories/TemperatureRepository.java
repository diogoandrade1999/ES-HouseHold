package ua.pt.temperature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.pt.temperature.entities.Temperature;

@Repository
public interface TemperatureRepository extends JpaRepository<Temperature, Long> {
}