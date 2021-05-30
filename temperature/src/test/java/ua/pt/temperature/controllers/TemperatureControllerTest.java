package ua.pt.temperature.controllers;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import ua.pt.temperature.entities.Temperature;
import ua.pt.temperature.repositories.TemperatureRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TemperatureControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TemperatureRepository temperatureRepository;

    @Test
    public void getRecentTemperatures() throws Exception {

        // create temperature object
        Date d = new Date();
        Temperature t = new Temperature();
        t.setHouseId(1);
        t.setRoomId(1);
        t.setTemperature(15);
        t.setDate(d);

        // save temperature object
        temperatureRepository.save(t);

        // get temperature object
        mvc.perform(get("/temperature/recent/1/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.temperature").value(15)).andExpect(jsonPath("$.houseId").value(1))
                .andExpect(jsonPath("$.roomId").value(1));
    }

}
