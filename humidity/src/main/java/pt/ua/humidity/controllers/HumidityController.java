package pt.ua.humidity.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pt.ua.humidity.entities.Humidity;
import pt.ua.humidity.services.HumidityService;

@RestController
@RequestMapping("/humidity")
public class HumidityController {

    @Autowired
    private HumidityService humidityService;

    @RequestMapping(value = "/{startDate}/{endDate}", method = RequestMethod.GET)
    public Iterable<Humidity> betweenDates(@PathVariable long startDate, @PathVariable long endDate) {
        return this.humidityService.getHumidityByDate(new Date(startDate * 1000L), new Date(endDate * 1000L));
    }

    @RequestMapping(value = "/{startDate}/{endDate}/{houseId}", method = RequestMethod.GET)
    public Iterable<Humidity> betweenDatesAndHouse(@PathVariable long startDate, @PathVariable long endDate,
            @PathVariable long houseId) {
        return this.humidityService.getHumidityByDateAndHouse(new Date(startDate * 1000L),
                new Date(endDate * 1000L), houseId);
    }

    @RequestMapping(value = "/{startDate}/{endDate}/{houseId}/{roomId}", method = RequestMethod.GET)
    public Iterable<Humidity> betweenDatesAndHouseAndRoom(@PathVariable long startDate, @PathVariable long endDate,
            @PathVariable long houseId, @PathVariable long roomId) {
        return this.humidityService.getHumidityByDateAndHouseAndRoom(new Date(startDate * 1000L),
                new Date(endDate * 1000L), houseId, roomId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Humidity save(@RequestBody Humidity humidity) {
        return this.humidityService.saveHumidity(humidity);
    }

    // added to get the most recent humidity
    // it should only by houseid, to avoid to call this api several times for each room in the household app side
    @RequestMapping(value = "/recent/{houseId}/{roomId}", method = RequestMethod.GET)
    public Humidity getRecentHumidity(@PathVariable long houseId, @PathVariable long roomId){
        return this.humidityService.getRecentHumidity(houseId, roomId);
    }

}