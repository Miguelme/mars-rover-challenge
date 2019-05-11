package self.edu.marsrover.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import self.edu.marsrover.rest.dto.MarsRoverBattery;
import self.edu.marsrover.rest.dto.MarsRoverDirection;
import self.edu.marsrover.rest.exception.BadRequestException;
import self.edu.marsrover.service.MarsRoverService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@RequestMapping("/mars-rover")
public class MarsRoverController {

    @Autowired
    private MarsRoverService marsRoverService;

    @RequestMapping(value = "move", method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.OK)
    public int move(@Valid @NotNull @RequestBody String marsRoverDirection) {
        MarsRoverDirection direction;
        try {
            direction = MarsRoverDirection.valueOf(marsRoverDirection);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException();
        }
        return marsRoverService.move(direction);
    }

    @RequestMapping(value = "charge", method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.OK)
    public MarsRoverBattery charge() {
        return marsRoverService.chargeBatteries();
    }

    @RequestMapping(value = "transmit", method = RequestMethod.POST)
    @ResponseStatus(code = HttpStatus.OK)
    public String transmitText(@Valid @NotNull @RequestBody String englishText){
        return marsRoverService.transmitText(englishText);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleException(BadRequestException e) {
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
