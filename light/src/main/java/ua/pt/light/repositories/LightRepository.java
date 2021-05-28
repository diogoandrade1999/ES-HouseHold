package ua.pt.light.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.pt.light.entities.Light;

@Repository
public interface LightRepository extends JpaRepository<Light, Long> {
}