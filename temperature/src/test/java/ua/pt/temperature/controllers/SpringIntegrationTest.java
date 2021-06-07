package ua.pt.temperature.controllers;

import java.util.Collections;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import ua.pt.temperature.entities.Temperature;
import ua.pt.temperature.repositories.TemperatureRepository;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("INTEGRATION_TEST")
@TestPropertySource(locations = "classpath:test.properties")
public class SpringIntegrationTest {

    @Autowired
    protected TestRestTemplate template;

    @Autowired
    private TemperatureRepository temperatureRepository;

    private ResponseEntity<String> response;
    private ResponseEntity<Temperature[]> response2;
    private ResponseEntity<Temperature> response3;

    private Temperature temperature;

    @Before
    public void before() {
        template.getRestTemplate().setInterceptors(Collections.singletonList((request, body, execution) -> {
            return execution.execute(request, body);
        }));
        temperature = new Temperature();
        temperature.setHouseId(1);
        temperature.setRoomId(1);
        temperature.setTemperature(15);
        temperature.setDate(new Date(1622236139 * 1000L));
        temperatureRepository.save(temperature);
    }

    @When("^the client calls /temperature/version$")
    public void the_client_issues_GET_version() throws Throwable {
        response = template.getForEntity("/temperature/version", String.class);
    }

    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        HttpStatus currentStatusCode = response.getStatusCode();
        assertThat("status code is incorrect : " + response.getBody(), currentStatusCode.value(), is(statusCode));
    }

    @And("^the client receives server version (.+)$")
    public void the_client_receives_server_version_body(String version) throws Throwable {
        assertThat(response.getBody(), is(version));
    }

    @When("^the client calls /temperature/recent/1/1$")
    public void the_client_issues_GET_last_temperature() throws Throwable {
        response3 = template.getForEntity("/temperature/recent/1/1", Temperature.class);
    }

    @And("^the client receives server last temperature with value (.+)$")
    public void the_client_receives_server_last_temperature_body(String temp) throws Throwable {
        Temperature last_temperature = response3.getBody();
        String t = String.valueOf(last_temperature.getTemperature());
        assertThat(t, is(temp));
    }

    @When("^the client calls /temperature/1622236039/1622236456$")
    public void the_client_issues_GET_temperature_by_date() throws Throwable {
        response2 = template.getForEntity("/temperature/1622236039/1622236456", Temperature[].class);
    }

    @When("^the client calls /temperature/1622236039/1622236456/1$")
    public void the_client_issues_GET_temperature_by_date_and_house() throws Throwable {
        response2 = template.getForEntity("/temperature/1622236039/1622236456/1", Temperature[].class);
    }

    @When("^the client calls /temperature/1622236039/1622236456/1/1$")
    public void the_client_issues_GET_temperature_by_date_and_house_room() throws Throwable {
        response2 = template.getForEntity("/temperature/1622236039/1622236456/1/1", Temperature[].class);
    }

    @And("^the client receives server temperature with value (.+)$")
    public void the_client_receives_server_temperature_body(String temp) throws Throwable {
        Temperature[] temperatures = response2.getBody();
        String t = String.valueOf(temperatures[0].getTemperature());
        assertThat(t, is(temp));
    }

}
