package rockets.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.time.Month;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class LaunchUnitTest
{
    private Launch target;

    @BeforeEach
    public void setUp() {
        target = new Launch();
    }


    @DisplayName("should throw exception when pass null to setLaunchDate function")
    @Test
    public void shouldThrowExceptionWhenSetFirstNameToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.setLaunchDate(null));
        assertEquals(null, exception.getMessage());
    }

    @DisplayName("should return False when the Data entered is invalid. Ex : Future date is invalid ")
    @Test
    public void shouldReturnFalseWhenUserEntersInValidDate() {
        LocalDate date = LocalDate.of(2025, Month.JANUARY, 1);
        target.setLaunchDate(date);
        assertFalse(date.equals(target.getLaunchDate()));
    }

/*    @DisplayName("should return True when the Data entered is invalid. Ex : Year is invalid - 001 ")
    @Test*/
//    public void shouldReturnFalseWhenUserEntersInValidDateTest2() {
//        LocalDate date = LocalDate.of(001, Month.JANUARY, 1);
//        target.setLaunchDate(date);
//       assertTrue(date.equals(target.getLaunchDate()));
//       assertFalse(date.equals(target.getLaunchDate()));
//    }

    @DisplayName("should return True when the Data entered is valid. Ex : Past and Today's date is valid ")
    @Test
    public void shouldReturnTrueWhenUserEntersValidDate() {
        LocalDate date = LocalDate.of(2015, 12, 01);
        target.setLaunchDate(date);
        assertTrue(date.equals(target.getLaunchDate()));
    }

    @DisplayName("should return True when Launch Service provider is set to valid object")
    @Test
    public void shouldReturnTrueWhenLSPIsValid()
    {
        LaunchServiceProvider a = new LaunchServiceProvider("abc",2000,"Australia");
        target.setLaunchServiceProvider(a);
        assertTrue(a.equals(target.getLaunchServiceProvider()));
    }

    @DisplayName("should return False when Launch Service provider is set to invalid/null object")
    @Test
    public void shouldReturnFalseWhenLSPIsInValid()
    {
        target.setLaunchServiceProvider(null);
        assertFalse(target.getLaunchServiceProvider() instanceof LaunchServiceProvider);
    }

    @DisplayName("should return False when Launch Service provider is set to invalid/null object")
    @Test
    public void shouldReturnFalseWhenLSPIsInValidTest2()
    {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setLaunchServiceProvider(new LaunchServiceProvider("",2000,"")));
        assertEquals("Name and Country cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should return True when Launch Vehicle is set to valid object")
    @Test
    public void shouldReturnTrueWhenSetLVIsValid()
    {
        LaunchServiceProvider lsp = new LaunchServiceProvider("abc",2000,"Australia");
        Rocket a = new Rocket("abc","USA",lsp);
        target.setLaunchVehicle(a);
        assertTrue(a.equals(target.getLaunchVehicle()));
    }

    @DisplayName("should return False when Launch Service provider is set to invalid/null object")
    @Test
    public void shouldReturnFalseWhenSetLaunchVehicleIsInValid()
    {
        target.setLaunchVehicle(null);
        assertFalse(target.getLaunchVehicle() instanceof Rocket);
    }

    @DisplayName("should return False when Launch Service provider is set to invalid/null object")
    @Test
    public void shouldReturnFalseWhenSetLaunchVehicleIsInValidTest2()
    {
        LaunchServiceProvider lsp = new LaunchServiceProvider("abc",2000,"Australia");
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.setLaunchVehicle(new Rocket(null,null,lsp)));
        assertEquals("The validated object is null", exception.getMessage());
    }

    @DisplayName("should return True when setPayload is set to valid Set")
    @Test
    public void shouldReturnTrueWhenSetPayloadIsValid()
    {
        Set<String> a = new HashSet<String>();
        a.add("abc");
        a.add("def");
        target.setPayload(a);
        assertTrue(a.equals(target.getPayload()));
    }

    @DisplayName("should return False when setPayload is set to invalid Set")
    @Test
    public void shouldReturnTrueWhenSetPayloadIsInValid()
    {
        Set<String> a = new HashSet<String>();
        target.setPayload(a);
        assertFalse(a.equals(target.getPayload()));
    }

    @DisplayName("should return True when setLaunchSite is set to valid ")
    @Test
    public void shouldReturnTrueWhenSetLaunchSiteIsValid()
    {
        String ls = "Launch Site";
        target.setLaunchSite(ls);
        assertTrue(ls.equals(target.getLaunchSite()));
    }

    @DisplayName("should return False when setLaunchSite is set to invalid value")
    @Test
    public void shouldReturnTrueWhenSetLaunchSiteIsInValid()
    {
        String ls = "Launch Site .....!!!!";
        target.setLaunchSite(ls);
        assertFalse(ls.equals(target.getLaunchSite()));
    }

    @DisplayName("should throw exception when pass a empty Orbit to setOrbit function")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    public void shouldThrowExceptionWhenSetOrbitToEmpty(String orbit) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setOrbit(orbit));
        assertEquals("orbit cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass null to setOrbit function")
    @Test
    public void shouldThrowExceptionWhenSetOrbitToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.setOrbit(null));
        assertEquals("orbit cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should return True when the Orbit entered is valid.")
    @Test
    public void shouldReturnTrueWhenUserEntersInValidOrbitName() {
        String name = "abc123";
        target.setOrbit(name);
        assertTrue(name.equals(target.getOrbit()));
    }

    @DisplayName("should throw exception when pass a empty Orbit to setOrbit function")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    public void shouldThrowExceptionWhenSetFunctionToEmpty(String function) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setFunction(function));
        assertEquals("function cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass null to setFunction function")
    @Test
    public void shouldThrowExceptionWhenSetFunctionToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.setFunction(null));
        assertEquals("function cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should return True when the Orbit entered is valid.")
    @Test
    public void shouldReturnTrueWhenUserEntersInValidFunctionName() {
        String name = "abc123";
        target.setFunction(name);
        assertTrue(name.equals(target.getFunction()));
    }


    @DisplayName("should return True when the price entered is valid.")
    @Test
    public void shouldReturnTrueWhenUserEntersValidPrice() {
        BigDecimal num = new BigDecimal(123);
        target.setPrice(num);
        assertTrue(num.equals(target.getPrice()));
    }

    @DisplayName("should return False when the price entered is invalid.")
    @Test
    public void shouldReturnTrueWhenUserEntersInValidPrice() {
        BigDecimal num = new BigDecimal(-1);
        target.setPrice(num);
        assertFalse(num.equals(target.getPrice()));
    }

    @DisplayName("should return True when the Launch outcome entered is valid.")
    @Test
    public void shouldReturnTrueWhenUserEntersValidLO() {
        Launch.LaunchOutcome a = Launch.LaunchOutcome.FAILED;
        target.setLaunchOutcome(a);
        assertTrue(a.equals(target.getLaunchOutcome()));
    }

    @DisplayName("should return False when the launch outcome entered is invalid.")
    @Test
    public void shouldReturnTrueWhenUserEntersInValidLO() {
        target.setPrice(null);
        assertFalse(target.getLaunchOutcome() instanceof Launch.LaunchOutcome);
    }
}