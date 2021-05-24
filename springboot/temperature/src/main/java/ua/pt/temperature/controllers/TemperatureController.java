package ua.pt.temperature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ua.pt.temperature.domains.Temperature;
import ua.pt.temperature.services.TemperatureService;

@RestController
@RequestMapping("/temperatures")
public class TemperatureController {

    @Autowired
    private TemperatureService temperatureService;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Temperature> all() {
        return this.temperatureService.listAllTemperatures();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Temperature save(@RequestBody Temperature temperature) {
        return this.temperatureService.saveTemperature(temperature);
    }
}