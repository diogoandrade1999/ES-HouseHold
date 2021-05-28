package ua.pt.humidity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ua.pt.humidity.entities.Humidity;
import ua.pt.humidity.services.HumidityService;

@RestController
@RequestMapping("/humidity")
public class HumidityController {

    @Autowired
    private HumidityService humidityService;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Humidity> all() {
        return this.humidityService.listAllHumiditys();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Humidity one(@PathVariable Long id) {
        return this.humidityService.getHumidityById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Humidity save(@RequestBody Humidity humidity) {
        return this.humidityService.saveHumidity(humidity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        this.humidityService.deleteHumidityById(id);
    }
}