package self.edu.marsrover.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MarsRoverLocation {

    private int[] locations = new int[20];

    private int currentLocation = 0;

    public int getNextLocation(MarsRoverDirection direction) {
        if (direction.equals(MarsRoverDirection.RIGHT)) {
            return (getCurrentLocation() + 1) % this.locations.length;
        } else {
            int nextLocation = (getCurrentLocation() - 1) % this.locations.length;
            return nextLocation < 0 ? nextLocation + this.locations.length : nextLocation;
        }
    }

    public void setNextLocation(int location) {
        this.currentLocation = location;
    }
}
