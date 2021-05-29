package ua.pt.light.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ua.pt.light.entities.Light;
import ua.pt.light.services.LightService;

@RestController
@RequestMapping("/light")
public class LightController {

    @Autowired
    private LightService lightService;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Light> all() {
        return this.lightService.listAllLights();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Light one(@PathVariable Long id) {
        return this.lightService.getLightById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Light save(@RequestBody Light light) {
        return this.lightService.saveLight(light);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        this.lightService.deleteLightById(id);
    }

    // added to get the most recent light
    // it should only by houseid, to avoid to call this api several times for each room in the household app side
    @RequestMapping(value = "/recent/{houseId}/{roomId}", method = RequestMethod.GET)
    public Light getRecentLuminosity(@PathVariable long houseId, @PathVariable long roomId){
        return lightService.getRecentLuminosity(houseId, roomId);
    }
}