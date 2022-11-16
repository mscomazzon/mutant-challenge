package challenge.mercadolibre.MercadoLibre.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import challenge.mercadolibre.MercadoLibre.MercadoLibreApplication;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MercadoLibreApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class MutantIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    String url;

    @BeforeEach
    public void init() {
        url = "http://localhost:" + port + "/mutant";
    }

    @Test
    public void postMutantOK() throws JSONException {
        String request[] = { "AAAA", "CAAA", "ATAA", "ATAA" };

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> requestEntity = new HttpEntity<>(createJson(request), headers);
        ResponseEntity<String> responseEntity = this.testRestTemplate.postForEntity(
                url,
                requestEntity,
                String.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void postMutantForbidden() {
        String request[] = { "ACCC", "CAAA", "TTCC", "ATAA" };

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> requestEntity = new HttpEntity<>(createJson(request), headers);
        ResponseEntity<String> responseEntity = this.testRestTemplate.postForEntity(
                url,
                requestEntity,
                String.class);
        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void postMutantBadRequest() {
        String request[] = { "ACKC", "CAAA", "TTCC", "ATAA" };

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> requestEntity = new HttpEntity<>(createJson(request), headers);
        ResponseEntity<String> responseEntity = this.testRestTemplate.postForEntity(
                url,
                requestEntity,
                String.class);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void postMutantBadRequestLength() {
        String request[] = { "ACC", "CAA", "TTC", "ATA" };

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> requestEntity = new HttpEntity<>(createJson(request), headers);
        ResponseEntity<String> responseEntity = this.testRestTemplate.postForEntity(
                url,
                requestEntity,
                String.class);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    private String createJson(String array[]) {
        StringBuilder builder = new StringBuilder();
        builder.append("{\"dna\":[");
        for (int i = 0; i < array.length; i++) {
            builder.append("\"");
            builder.append(array[i]);
            builder.append("\"");
            if (i != array.length - 1)
                builder.append(",");
        }
        builder.append("]}");
        return builder.toString();
    }
}
