package pt.ua.household.services;

import pt.ua.household.entities.House;

public interface HouseService {

    House getHouseById(long id);

    House saveHouse(House house);

    void removeHouse(House house);

}
