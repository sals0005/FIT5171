package rockets.model;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class RocketUnitTest {

    private Rocket target;
    @BeforeEach
    public void setUp() {
        LaunchServiceProvider lsp = new LaunchServiceProvider("ABC",1990,"Japan");
        target = new Rocket("abc","USA",lsp);
    }

    @AfterEach
    public void tearDown() {
    }

    @DisplayName("should throw exception when pass a empty name to setName function")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    public void shouldThrowExceptionWhenSetNameToEmpty(String name) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setName(name));
        assertEquals("Name cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should return True when the name entered is valid. ")
    @Test
    public void shouldReturnTrueWhenUserEntersValidName() {
        String name = "RocketABC";
        target.setName(name);
        assertTrue(target.getName().equals(name));
    }

    @DisplayName("should return False when the name entered is invalid. ")
    @Test
    public void shouldReturnTrueWhenUserEntersInValidName() {
        String name = "RocketABC";
        target.setName(name);
        assertFalse(target.getName().equals("NAMEabc"));
    }

    @DisplayName("should throw exception when pass a empty  to setCountry function")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    public void shouldThrowExceptionWhenSetCountryToEmpty(String country) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setCountry(country));
        assertEquals("Name cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should return True when the country entered is valid. ")
    @Test
    public void shouldReturnTrueWhenUserEntersValidCountry() {
        String name = "Australia";
        target.setName(name);
        assertTrue(target.getName().equals(name));
    }

    @DisplayName("should return False when the name entered is invalid. ")
    @Test
    public void shouldReturnTrueWhenUserEntersInValidCountry() {
        String name = "Australia";
        target.setName(name);
        assertFalse(target.getName().equals("USA"));
    }


    @DisplayName("should return True when the manufacturer entered is valid. ")
    @Test
    public void shouldReturnTrueWhenUserEntersValidManufacturer() {

        assertTrue(target.getManufacturer() instanceof LaunchServiceProvider);
    }


    @DisplayName("should create rocket successfully when given right parameters to constructor")
    @Test
    public void shouldConstructRocketObject() {
        String name = "BFR";
        String country = "USA";
        LaunchServiceProvider manufacturer = new LaunchServiceProvider("SpaceX", 2002, "USA");
        Rocket bfr = new Rocket(name, country, manufacturer);
        assertNotNull(bfr);
    }

    @DisplayName("should throw exception when given null manufacturer to constructor")
    @Test
    public void shouldThrowExceptionWhenNoManufacturerGiven() {
        String name = "BFR";
        String country = "USA";
        assertThrows(NullPointerException.class, () -> new Rocket(name, country, null));
    }

    @DisplayName("should set rocket massToLEO value")
    @ValueSource(strings = {"10000", "15000"})
    public void shouldSetMassToLEOWhenGivenCorrectValue(String massToLEO) {
        String name = "BFR";
        String country = "USA";
        LaunchServiceProvider manufacturer = new LaunchServiceProvider("SpaceX", 2002, "USA");

        Rocket bfr = new Rocket(name, country, manufacturer);

        bfr.setMassToLEO(massToLEO);
        assertEquals(massToLEO, bfr.getMassToLEO());
    }

    @DisplayName("should throw exception when set massToLEO to null")
    @Test
    public void shouldThrowExceptionWhenSetMassToLEOToNull() {
        String name = "BFR";
        String country = "USA";
        LaunchServiceProvider manufacturer = new LaunchServiceProvider("SpaceX", 2002, "USA");
        Rocket bfr = new Rocket(name, country, manufacturer);
        assertThrows(NullPointerException.class, () -> bfr.setMassToLEO(null));
    }

    @DisplayName("should throw exception when set massToGTO to null")
    @Test
    public void shouldThrowExceptionWhenSetMassToGTOToNull() {
        String name = "BFR";
        String country = "USA";
        LaunchServiceProvider manufacturer = new LaunchServiceProvider("SpaceX", 2002, "USA");
        Rocket bfr = new Rocket(name, country, manufacturer);
        assertThrows(NullPointerException.class, () -> bfr.setMassToGTO(null));
    }

    @DisplayName("should throw exception when set massToOther to null")
    @Test
    public void shouldThrowExceptionWhenSetMassToOtherToNull() {
        String name = "BFR";
        String country = "USA";
        LaunchServiceProvider manufacturer = new LaunchServiceProvider("SpaceX", 2002, "USA");
        Rocket bfr = new Rocket(name, country, manufacturer);
        assertThrows(NullPointerException.class, () -> bfr.setMassToOther(null));
    }

    @DisplayName("should throw exception when pass null to Rocket constructor ")
    @Test
    public void shouldThrowExceptionWhenSetRocketConstructorToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> new Rocket(null,null ,null));
        assertEquals("The validated object is null", exception.getMessage());
    }

    @DisplayName("should return True when constructor set name,country,manufacturer correctly")
    @Test
    public void shouldReturnTrueWhenConstructorInputAndOutputAreSame() {
        LaunchServiceProvider manufacturer = new LaunchServiceProvider("SpaceX", 2002, "USA");
        target = new Rocket("abc","Australia",manufacturer);
        String name = target.getName();
        String country = target.getCountry();
        LaunchServiceProvider manufacturerTest = target.getManufacturer();
        assertTrue(name == "abc" && country == "Australia" && manufacturerTest.equals(manufacturer));
    }


    @DisplayName("should return False when two objects of different attribute values are equal")
    @Test
    public void shouldReturnFalseWhenTwoObjectsAreUnequal() {
        LaunchServiceProvider manufacturer = new LaunchServiceProvider("SpaceX", 2002, "USA");
        Rocket a = new Rocket("abc","Australia",manufacturer);
        Rocket b = new Rocket("def","Australia",manufacturer);

        assertFalse(a.equals(b));
    }

    @DisplayName("should return True when two objects of same attribute values are equal")
    @Test
    public void shouldReturnTrueWhenTwoObjectsAreEqual() {
        LaunchServiceProvider manufacturer = new LaunchServiceProvider("SpaceX", 2002, "USA");
        Rocket a = new Rocket("abc","Australia",manufacturer);
        Rocket b = new Rocket("abc","Australia",manufacturer);

        assertTrue(a.equals(b));
    }

    @DisplayName("should return True when two objects of same attribute values have same ToString outputs")
    @Test
    public void shouldReturnTrueWhenTwoObjectsAreEqualInStringComaparison() {
        LaunchServiceProvider manufacturer = new LaunchServiceProvider("SpaceX", 2002, "USA");
        Rocket a = new Rocket("abc","Australia",manufacturer);
        Rocket b = new Rocket("abc","Australia",manufacturer);
        a.setMassToGTO("12345");
        a.setMassToLEO("123");
        b.setMassToGTO("12345");
        b.setMassToLEO("123");
        assertTrue(a.toString().equals(b.toString()));
    }

    @DisplayName("should return False when two objects of different attribute values have same ToString outputs")
    @Test
    public void shouldReturnFalseWhenTwoObjectsAreUnEqualInStringComaparison() {
        LaunchServiceProvider manufacturer = new LaunchServiceProvider("SpaceX", 2002, "USA");
        Rocket a = new Rocket("abc","Australia",manufacturer);
        Rocket b = new Rocket("def","Australia",manufacturer);
        a.setMassToGTO("12345");
        a.setMassToLEO("123");
        b.setMassToGTO("12345");
        b.setMassToLEO("123");
        assertFalse(a.toString().equals(b.toString()));
    }

    @DisplayName("should return True when two objects of same attribute value have same Hash")
    @Test
    public void shouldReturnTrueWhenTwoObjectsHashCodeAreEqual() {
        LaunchServiceProvider manufacturer = new LaunchServiceProvider("SpaceX", 2002, "USA");
        Rocket a = new Rocket("abc","Australia",manufacturer);
        Rocket b = new Rocket("abc","Australia",manufacturer);
        assertTrue(a.hashCode()== b.hashCode());
    }

    @DisplayName("should return False when two objects of same attribute value have same Hash")
    @Test
    public void shouldReturnTrueWhenTwoObjectsHashCodeAreUnEqual() {
        LaunchServiceProvider manufacturer = new LaunchServiceProvider("SpaceX", 2002, "USA");
        Rocket a = new Rocket("abc","Australia",manufacturer);
        Rocket b = new Rocket("def","Australia",manufacturer);
        assertFalse(a.hashCode()== b.hashCode());
    }
}