package self.edu.marsrover;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import self.edu.marsrover.rest.dto.MarsRoverBattery;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MarsRoverApplicationTests {

    protected static final String BASE_URI = "http://localhost:%s/mars-rover/%s";

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void testMove() {
        String moveUrl = "move";
        String url = String.format(BASE_URI, port, moveUrl);
        Integer location = restTemplate.postForEntity(url, "RIGHT", Integer.class).getBody();
        Assert.assertEquals("The expected location is 1",1, location.intValue());
        location = restTemplate.postForEntity(url, "LEFT", Integer.class).getBody();
        Assert.assertEquals("The expected location is 0",0, location.intValue());
        location = restTemplate.postForEntity(url, "LEFT", Integer.class).getBody();
        Assert.assertEquals("The expected location is 19",19, location.intValue());
        HttpStatus status = restTemplate.postForEntity(url, null, null).getStatusCode();
        Assert.assertEquals(HttpStatus.BAD_REQUEST, status);
        status = restTemplate.postForEntity(url, "TEST", null).getStatusCode();
        Assert.assertEquals(HttpStatus.BAD_REQUEST, status);

    }

    @Test
    public void testCharge() {
        String chargeUrl = "charge";
        String url = String.format(BASE_URI, port, chargeUrl);
        MarsRoverBattery battery = restTemplate.postForEntity(url, null, MarsRoverBattery.class).getBody();
        Assert.assertEquals(MarsRoverBattery.MEDIUM, battery);
        battery = restTemplate.postForEntity(url, null, MarsRoverBattery.class).getBody();
        Assert.assertEquals(MarsRoverBattery.FULL, battery);
        battery = restTemplate.postForEntity(url, null, MarsRoverBattery.class).getBody();
        Assert.assertEquals(MarsRoverBattery.FULL, battery);
    }

    @Test
    public void testTransmitText() {
        String transmitUrl = "transmit";
        String url = String.format(BASE_URI, port, transmitUrl);
        String textTransmitted1 = restTemplate.postForEntity(url, "Hola", String.class).getBody();
        String textTransmitted2 = restTemplate.postForEntity(url, "Hola", String.class).getBody();
        Assert.assertEquals(textTransmitted1, textTransmitted2);
        HttpStatus status = restTemplate.postForEntity(url, null, String.class).getStatusCode();
        Assert.assertEquals(HttpStatus.BAD_REQUEST, status);
    }


}
