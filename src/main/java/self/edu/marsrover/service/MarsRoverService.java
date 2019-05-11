package self.edu.marsrover.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import self.edu.marsrover.rest.dto.MarsRover;
import self.edu.marsrover.rest.dto.MarsRoverBattery;
import self.edu.marsrover.rest.dto.MarsRoverDirection;

import java.util.Base64;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Service
@Slf4j
public class MarsRoverService {

    private MarsRover marsRover = new MarsRover();

    public int move(MarsRoverDirection marsRoverDirection) {
        int nextLocation = marsRover.getMarsRoverLocation().getNextLocation(marsRoverDirection);
        marsRover.getMarsRoverLocation().setCurrentLocation(nextLocation);
        return marsRover.getMarsRoverLocation().getCurrentLocation();
    }

    public MarsRoverBattery chargeBatteries() {
        MarsRoverBattery marsRoverBattery = marsRover.getMarsRoverBattery();

        if (marsRoverBattery.equals(MarsRoverBattery.LOW)) marsRover.setMarsRoverBattery(MarsRoverBattery.MEDIUM);
        if (marsRoverBattery.equals(MarsRoverBattery.MEDIUM)) marsRover.setMarsRoverBattery(MarsRoverBattery.FULL);

        return marsRover.getMarsRoverBattery();
    }

    public String transmitText(String englishText) {
        return Base64.getEncoder().encodeToString(englishText.getBytes());
    }
}
