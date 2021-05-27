package ua.pt.temperature.controllers;

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

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Temperature> all() {
        return this.temperatureService.listAllTemperatures();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Temperature one(@PathVariable Long id) {
        return this.temperatureService.getTemperatureById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Temperature save(@RequestBody Temperature temperature) {
        return this.temperatureService.saveTemperature(temperature);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        this.temperatureService.deleteTemperatureById(id);
    }
}