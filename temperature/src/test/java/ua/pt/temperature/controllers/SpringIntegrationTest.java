package ua.pt.temperature.controllers;

import java.util.Collections;

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

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("INTEGRATION_TEST")
@TestPropertySource(locations = "classpath:test.properties")
public class SpringIntegrationTest {

    @Autowired
    protected TestRestTemplate template;

    private ResponseEntity<String> response;

    @Before
    public void before() {
        template.getRestTemplate().setInterceptors(Collections.singletonList((request, body, execution) -> {
            return execution.execute(request, body);
        }));
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

}
