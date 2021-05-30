package ua.pt.luminosity.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ua.pt.luminosity.entities.Luminosity;
import ua.pt.luminosity.services.LuminosityService;

@RestController
@RequestMapping("/luminosity")
public class LuminosityController {

    @Autowired
    private LuminosityService luminosityService;

    @RequestMapping(value = "/{startDate}/{endDate}", method = RequestMethod.GET)
    public Iterable<Luminosity> betweenDates(@PathVariable long startDate, @PathVariable long endDate) {
        return this.luminosityService.getLuminosityByDate(new Date(startDate * 1000L), new Date(endDate * 1000L));
    }

    @RequestMapping(value = "/{startDate}/{endDate}/{houseId}", method = RequestMethod.GET)
    public Iterable<Luminosity> betweenDatesAndHouse(@PathVariable long startDate, @PathVariable long endDate,
            @PathVariable long houseId) {
        return this.luminosityService.getLuminosityByDateAndHouse(new Date(startDate * 1000L),
                new Date(endDate * 1000L), houseId);
    }

    @RequestMapping(value = "/{startDate}/{endDate}/{houseId}/{roomId}", method = RequestMethod.GET)
    public Iterable<Luminosity> betweenDatesAndHouseAndRoom(@PathVariable long startDate, @PathVariable long endDate,
            @PathVariable long houseId, @PathVariable long roomId) {
        return this.luminosityService.getLuminosityByDateAndHouseAndRoom(new Date(startDate * 1000L),
                new Date(endDate * 1000L), houseId, roomId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Luminosity save(@RequestBody Luminosity luminosity) {
        return this.luminosityService.saveLuminosity(luminosity);
    }

    // added to get the most recent luminosity
    // it should only by houseid, to avoid to call this api several times for each
    // room in the household app side
    @RequestMapping(value = "/recent/{houseId}/{roomId}", method = RequestMethod.GET)
    public Luminosity getRecentLuminosity(@PathVariable long houseId, @PathVariable long roomId) {
        return this.luminosityService.getRecentLuminosity(houseId, roomId);
    }

}