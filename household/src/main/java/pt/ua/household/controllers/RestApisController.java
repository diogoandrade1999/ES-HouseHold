package pt.ua.household.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import pt.ua.household.model.Humidity;
import pt.ua.household.model.Luminosity;
import pt.ua.household.model.Temperature;

@RestController
@RequestMapping("/api")
public class RestApisController {

    @Value(value = "${spring.api.temperature-url}")
    private String temperatureUrl;

    @Value(value = "${spring.api.luminosity-url}")
    private String luminosityUrl;

    @Value(value = "${spring.api.humidity-url}")
    private String humidityUrl;

    @RequestMapping(value = "/{api}/{startDate}/{endDate}/{houseId}", method = RequestMethod.GET)
    public Object[] getByHouse(@PathVariable String api, @PathVariable long startDate, @PathVariable long endDate,
            @PathVariable long houseId) {
        String apiUrl = "";
        if (api.equals("temperature"))
            apiUrl = temperatureUrl;
        else if (api.equals("luminosity"))
            apiUrl = luminosityUrl;
        else if (api.equals("humidity"))
            apiUrl = humidityUrl;
        else
            return null;
        String uri = apiUrl + "/" + api + "/" + startDate + "/" + endDate + "/" + houseId;
        RestTemplate restTemplate = new RestTemplate();
        if (api.equals("temperature"))
            return restTemplate.getForObject(uri, Temperature[].class);
        else if (api.equals("luminosity"))
            return restTemplate.getForObject(uri, Luminosity[].class);
        else if (api.equals("humidity"))
            return restTemplate.getForObject(uri, Humidity[].class);
        return null;
    }

    @RequestMapping(value = "/{api}/{startDate}/{endDate}/{houseId}/{roomId}", method = RequestMethod.GET)
    public Object[] getByHouseAdnRoom(@PathVariable String api, @PathVariable long startDate,
            @PathVariable long endDate, @PathVariable long houseId, @PathVariable long roomId) {
        String apiUrl = "";
        if (api.equals("temperature"))
            apiUrl = temperatureUrl;
        else if (api.equals("luminosity"))
            apiUrl = luminosityUrl;
        else if (api.equals("humidity"))
            apiUrl = humidityUrl;
        else
            return null;
        String uri = apiUrl + "/" + api + "/" + startDate + "/" + endDate + "/" + houseId + "/" + roomId;
        RestTemplate restTemplate = new RestTemplate();
        if (api.equals("temperature"))
            return restTemplate.getForObject(uri, Temperature[].class);
        else if (api.equals("luminosity"))
            return restTemplate.getForObject(uri, Luminosity[].class);
        else if (api.equals("humidity"))
            return restTemplate.getForObject(uri, Humidity[].class);
        return null;
    }

}