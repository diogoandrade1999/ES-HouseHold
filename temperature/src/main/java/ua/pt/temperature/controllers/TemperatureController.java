package ua.pt.temperature.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ua.pt.temperature.entities.Temperature;
import ua.pt.temperature.services.TemperatureService;

@RestController
@RequestMapping("/temperature")
public class TemperatureController {

    @Autowired
    private TemperatureService temperatureService;

    @RequestMapping(value = "/{startDate}/{endDate}", method = RequestMethod.GET)
    public Iterable<Temperature> betweenDates(@PathVariable long startDate, @PathVariable long endDate) {
        return this.temperatureService.getTemperatureByDate(new Date(startDate * 1000L), new Date(endDate * 1000L));
    }

    @RequestMapping(value = "/{startDate}/{endDate}/{houseId}", method = RequestMethod.GET)
    public Iterable<Temperature> betweenDatesAndHouse(@PathVariable long startDate, @PathVariable long endDate,
            @PathVariable long houseId) {
        return this.temperatureService.getTemperatureByDateAndHouse(new Date(startDate * 1000L),
                new Date(endDate * 1000L), houseId);
    }

    @RequestMapping(value = "/{startDate}/{endDate}/{houseId}/{roomId}", method = RequestMethod.GET)
    public Iterable<Temperature> betweenDatesAndHouseAndRoom(@PathVariable long startDate, @PathVariable long endDate,
            @PathVariable long houseId, @PathVariable long roomId) {
        return this.temperatureService.getTemperatureByDateAndHouseAndRoom(new Date(startDate * 1000L),
                new Date(endDate * 1000L), houseId, roomId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Temperature save(@RequestBody Temperature temperature) {
        return this.temperatureService.saveTemperature(temperature);
    }

}