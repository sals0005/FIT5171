package rockets.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RocketFamilyUnitTest {
    private RocketFamily target;
    private String family = "Atlas";

    @BeforeEach
    public void setUp() {
        target = new RocketFamily(family);
    }

    @DisplayName("should throw exception when pass null to RocketFamily family")
    @Test
    public void shouldThrowExceptionWhenFamilyIsNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> new RocketFamily(null));
        assertEquals("family name cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass a empty name to Rocket name")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    public void shouldThrowExceptionWhenFamilyIsEmpty(String family) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new RocketFamily(family));
        assertEquals("family name cannot be null or empty", exception.getMessage());
    }


    @DisplayName("should throw exception when pass null to variation")
    @Test
    public void shouldThrowExceptionWhenVariationIsNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.setVariation(null));
        assertEquals("variation cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass a empty name to Rocket name")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    public void shouldThrowExceptionWhenVariationIsEmpty(String variation) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setVariation(variation));
        assertEquals("variation cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception variation is not valid")
    @ParameterizedTest
    @ValueSource(strings = {"Atas1", "atlas2", "GCE"})
    public void shouldThrowExceptionWhenVariationIsNotValid(String variation) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setVariation(variation));
        assertEquals("Variation name is not valid", exception.getMessage());
    }

    @DisplayName("should throw exception when pass null to variation")
    @Test
    public void shouldThrowExceptionWhenSubVariationIsNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.setSubVariation(null));
        assertEquals("subVariation cannot be null", exception.getMessage());
    }

    //unit test that checks the mass to leo/gto can not be set to null
    @DisplayName("should throw exception when pass null to mass to leo/gto/other function")
    @Test
    public void shouldThrowExceptionWhenMassToLeoGtoIsNull() {
        NullPointerException exceptionLeo = assertThrows(NullPointerException.class, () -> target.setPayloadToLEOCapacity(null));
        NullPointerException exceptionGto = assertThrows(NullPointerException.class, () -> target.setPayloadToGTOCapacity(null));
        assertEquals("mass to leo cannot be null", exceptionLeo.getMessage());
        assertEquals("mass to gto cannot be null", exceptionGto.getMessage());
    }

    //unit test that checks if the mass to leo/gto is valid
    @DisplayName("should be a valid mass to leo/gto")
    @ParameterizedTest
    @ValueSource(strings = {"-11", "0", "110.23", "300000"})
    public void shouldThrowExceptionWhenMassIsNotValid(String mass) {
        IllegalArgumentException exceptionLeo = assertThrows(IllegalArgumentException.class, () -> target.setPayloadToLEOCapacity(mass));
        IllegalArgumentException exceptionGto= assertThrows(IllegalArgumentException.class, () -> target.setPayloadToGTOCapacity(mass));
        assertEquals("Mass should be an integer greater than 0 and less than 200000", exceptionLeo.getMessage());
        assertEquals("Mass should be an integer greater than 0 and less than 200000", exceptionGto.getMessage());
    }

}
