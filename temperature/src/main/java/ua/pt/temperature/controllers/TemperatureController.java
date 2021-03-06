package ua.pt.temperature.controllers;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TemperatureService temperatureService;

    @RequestMapping(value = "/{startDate}/{endDate}", method = RequestMethod.GET)
    public Iterable<Temperature> betweenDates(@PathVariable long startDate, @PathVariable long endDate) {
        this.logger.info("GET -> Temperature by Date");
        return this.temperatureService.getTemperatureByDate(new Date(startDate * 1000L), new Date(endDate * 1000L));
    }

    @RequestMapping(value = "/{startDate}/{endDate}/{houseId}", method = RequestMethod.GET)
    public Iterable<Temperature> betweenDatesAndHouse(@PathVariable long startDate, @PathVariable long endDate,
            @PathVariable long houseId) {
        // this.logger.info("GET -> Temperature from House ID: {}", houseId);
        return this.temperatureService.getTemperatureByDateAndHouse(new Date(startDate * 1000L),
                new Date(endDate * 1000L), houseId);
    }

    @RequestMapping(value = "/{startDate}/{endDate}/{houseId}/{roomId}", method = RequestMethod.GET)
    public Iterable<Temperature> betweenDatesAndHouseAndRoom(@PathVariable long startDate, @PathVariable long endDate,
            @PathVariable long houseId, @PathVariable long roomId) {
        // this.logger.info("GET -> Temperature from Room ID: {}", roomId);
        return this.temperatureService.getTemperatureByDateAndHouseAndRoom(new Date(startDate * 1000L),
                new Date(endDate * 1000L), houseId, roomId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Temperature save(@RequestBody Temperature temperature) {
        // this.logger.info("POST -> Save Temperature from Room ID: {}",
        // temperature.getRoomId());
        return this.temperatureService.saveTemperature(temperature);
    }

    // added to get the most recent temperature
    // it should only by houseid, to avoid to call this api several times for each
    // room in the household app side
    @RequestMapping(value = "/recent/{houseId}/{roomId}", method = RequestMethod.GET)
    public Temperature getRecentTemperature(@PathVariable long houseId, @PathVariable long roomId) {
        // this.logger.info("GET -> Recent Temperature from Room ID: {}", roomId);
        return this.temperatureService.getRecentTemperature(houseId, roomId);
    }

    @RequestMapping(value = "/version", method = RequestMethod.GET)
    public String getVersion() {
        this.logger.info("GET -> Version: 1.0");
        return "1.0";
    }

}