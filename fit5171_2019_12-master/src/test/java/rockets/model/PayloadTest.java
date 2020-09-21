package rockets.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class PayloadTest {

    private Payload payload;

    @BeforeEach
    public void setUp() {
        payload = new Payload("satellite", 0.0, "mission");
    }

    //type can't be empty
    @DisplayName("should throw exception when pass an empty type to setType function")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    public void shouldThrowExceptionWhenSetTypeToEmpty(String type) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> payload.setType(type));
        assertEquals("type cannot be null or empty", exception.getMessage());
    }

    //type can't be null
    @DisplayName("should throw exception when pass null to setType function")
    @Test
    public void shouldThrowExceptionWhenSetTypeToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> payload.setType(null));
        assertEquals("type cannot be null or empty", exception.getMessage());
    }

    //weight has to be a number and can't be empty
    @DisplayName("should throw exception when pass a value that's not a number to setWeight function")
    @ParameterizedTest
    @ValueSource(strings = {"test", "", " "})
    public void shouldThrowExceptionWhenNotSetWeightToNumber(String strWeight) {
        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> payload.setWeight(strWeight));
        assertEquals("weight has to be a number and cannot be empty", exception.getMessage());
    }

    //weight can't be null
    @DisplayName("should throw exception when pass null to setWeight function")
    @Test
    public void shouldThrowExceptionWhenSetWeightToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> payload.setWeight(null));
        assertEquals("weight cannot be null", exception.getMessage());
    }

    //weight can't be negative a negative number
    @DisplayName("should throw exception when pass a negative number to setWeight function")
    @ParameterizedTest
    @ValueSource(strings = {"-1.1", "-0.1"})
    public void shouldThrowExceptionWhenSetWeightToEmpty(String strWeight) {
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class, () -> payload.setWeight(strWeight));
        assertEquals("weight cannot be a negative number", exception.getMessage());
    }

    //mission can't be empty
    @DisplayName("should throw exception when pass an empty mission to setMission function")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    public void shouldThrowExceptionWhenSetMissionToEmpty(String mission) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> payload.setMission(mission));
        assertEquals("mission cannot be null or empty", exception.getMessage());
    }

    //mission can't be null
    @DisplayName("should throw exception when pass null to setMission function")
    @Test
    public void shouldThrowExceptionWhenSetMissionToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> payload.setMission(null));
        assertEquals("mission cannot be null or empty", exception.getMessage());
    }



}