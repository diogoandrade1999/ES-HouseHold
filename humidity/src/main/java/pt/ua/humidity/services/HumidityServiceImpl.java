package pt.ua.humidity.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.ua.humidity.entities.Humidity;
import pt.ua.humidity.repositories.HumidityRepository;

@Service
public class HumidityServiceImpl implements HumidityService {

    @Autowired
    private HumidityRepository humidityRepository;

    @Override
    public Humidity saveHumidity(Humidity humidity) {
        return this.humidityRepository.save(humidity);
    }

    @Override
    public Iterable<Humidity> getHumidityByDate(Date startDate, Date endDate) {
        return this.humidityRepository.findByDateBetween(startDate, endDate);
    }

    @Override
    public Iterable<Humidity> getHumidityByDateAndHouse(Date startDate, Date endDate, long houseId) {
        return this.humidityRepository.findByDateBetweenAndHouseId(startDate, endDate, houseId);
    }

    @Override
    public Iterable<Humidity> getHumidityByDateAndHouseAndRoom(Date startDate, Date endDate, long houseId,
            long roomId) {
        return this.humidityRepository.findByDateBetweenAndHouseIdAndRoomId(startDate, endDate, houseId, roomId);
    }

    @Override
    public Humidity getRecentHumidity(long houseId, long roomId) {
        return this.humidityRepository.findFirstByHouseIdAndRoomIdOrderByDateDesc(houseId, roomId);
    }

}
