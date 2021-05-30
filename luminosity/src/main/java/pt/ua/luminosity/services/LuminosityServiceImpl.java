package ua.pt.luminosity.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.pt.luminosity.entities.Luminosity;
import ua.pt.luminosity.repositories.LuminosityRepository;

@Service
public class LuminosityServiceImpl implements LuminosityService {

    @Autowired
    private LuminosityRepository luminosityRepository;

    @Override
    public Luminosity saveLuminosity(Luminosity luminosity) {
        return this.luminosityRepository.save(luminosity);
    }

    @Override
    public Iterable<Luminosity> getLuminosityByDate(Date startDate, Date endDate) {
        return this.luminosityRepository.findByDateBetween(startDate, endDate);
    }

    @Override
    public Iterable<Luminosity> getLuminosityByDateAndHouse(Date startDate, Date endDate, long houseId) {
        return this.luminosityRepository.findByDateBetweenAndHouseId(startDate, endDate, houseId);
    }

    @Override
    public Iterable<Luminosity> getLuminosityByDateAndHouseAndRoom(Date startDate, Date endDate, long houseId,
            long roomId) {
        return this.luminosityRepository.findByDateBetweenAndHouseIdAndRoomId(startDate, endDate, houseId, roomId);
    }

    @Override
    public Luminosity getRecentLuminosity(long houseId, long roomId) {
        return this.luminosityRepository.findFirstByHouseIdAndRoomIdOrderByDateDesc(houseId, roomId);
    }

}
