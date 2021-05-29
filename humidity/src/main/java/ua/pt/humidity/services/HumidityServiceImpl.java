package ua.pt.humidity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.pt.humidity.entities.Humidity;
import ua.pt.humidity.repositories.HumidityRepository;

@Service
public class HumidityServiceImpl implements HumidityService {

    @Autowired
    private HumidityRepository humidityRepository;

    @Override
    public Iterable<Humidity> listAllHumiditys() {
        return this.humidityRepository.findAll();
    }

    @Override
    public Humidity saveHumidity(Humidity humidity) {
        return this.humidityRepository.save(humidity);
    }

    @Override
    public long count() {
        return this.humidityRepository.count();
    }

    @Override
    public Humidity getHumidityById(long id) {
        return this.humidityRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteHumidityById(long id) {
        this.humidityRepository.deleteById(id);
    }

    @Override
    public Humidity getRecentHumidity(long houseId, long roomId){
        return humidityRepository.findFirstByHouseIdAndRoomIdOrderByDateDesc(houseId, roomId);
    }
}
