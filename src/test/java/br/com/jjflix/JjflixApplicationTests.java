package br.com.jjflix;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

@SpringBootTest(classes = JjflixApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JjflixApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getUrl() {
	return "http://localhost:" + port;
    }

    @Test
    public void testPegarTodosVideos() {
	HttpHeaders headers = new HttpHeaders();

	HttpEntity<String> entity = new HttpEntity<String>(null, headers);

	ResponseEntity<String> response = restTemplate.exchange(getUrl() + "/videos", HttpMethod.GET, entity,
		String.class);

	Assert.notNull(response.getBody(), "Deu ruim");
    }

}
