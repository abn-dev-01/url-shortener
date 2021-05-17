package com.abndev.shortener;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ActuatorHealthIT {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	@DisplayName("Have to return Actuator health: UP.")
	void shouldReturnCorrectActuatorHealthValue() {
		Map<String, String> response = restTemplate.exchange(
			"http://localhost:" + port + "/actuator/health",
			HttpMethod.GET,
			null,
			new ParameterizedTypeReference<Map<String, String>>() {}
		).getBody();
		
		assertNotNull(response);
		assertEquals("UP", response.get("status"));
	}
}
