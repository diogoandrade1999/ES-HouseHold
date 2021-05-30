package pt.ua.household.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pt.ua.household.entities.House;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {

}
