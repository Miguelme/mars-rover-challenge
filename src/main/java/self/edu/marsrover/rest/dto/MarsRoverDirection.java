package self.edu.marsrover.rest.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum MarsRoverDirection {
    LEFT("LEFT"),
    RIGHT("RIGHT");

    private String code;

    MarsRoverDirection(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
    @Override
    public String toString() {
        return this.code;
    }

    @JsonCreator
    public static MarsRoverDirection create(String value) {
        if(value == null) {
            throw new IllegalArgumentException();
        }
        for(MarsRoverDirection v : values()) {
            if(value.equals(v.getCode())) {
                return v;
            }
        }
        throw new IllegalArgumentException();
    }
}
