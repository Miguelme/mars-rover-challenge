package self.edu.marsrover.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MarsRover {

    private MarsRoverLocation marsRoverLocation = new MarsRoverLocation();

    private MarsRoverBattery marsRoverBattery = MarsRoverBattery.LOW;

    private MarsRoverScreen marsRoverScreen;
}
