package pt.ua.household.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.ua.household.entities.House;
import pt.ua.household.repositories.HouseRepository;

@Service
public class HouseServiceImpl implements HouseService {

    @Autowired
    private HouseRepository houseRepository;

    @Override
    public House getHouseById(long id) {
        return this.houseRepository.findById(id).orElse(null);
    }

    @Override
    public House saveHouse(House house) {
        return this.houseRepository.save(house);
    }

    @Override
    public void removeHouse(House house) {
        this.houseRepository.delete(house);
    }

}
