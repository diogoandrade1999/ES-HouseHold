package ua.pt.temperature.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import ua.pt.temperature.entities.Temperature;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations = "classpath:test.properties")
public class TemperatureRepositoryTest {

    @Autowired
    private TemperatureRepository temperatureRepository;

    private Temperature temperature;
    private Date date;

    @Before
    public void setUp() {
        date = new Date();
        temperature = new Temperature();
        temperature.setDate(date);
        temperature.setHouseId(1);
        temperature.setRoomId(1);
        temperature.setTemperature(15);
    }

    @After
    public void tearDown() {
        temperatureRepository.deleteAll();
        temperature = null;
    }

    @Test
    public void givenTemperatureToAddShouldReturnAddedTemperature() {
        temperatureRepository.save(temperature);
        Temperature fetchedTemperature = temperatureRepository.findById(temperature.getDate()).get();
        assertTrue(date.getTime() / 1000 == fetchedTemperature.getDate().getTime() / 1000);
    }

    @Test
    public void shouldNotFindTemperaturesRepositoryIsEmpty() {
        Iterable<Temperature> temperature = temperatureRepository.findAll();
        assertThat(temperature).isEmpty();
    }
}
