package rockets.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import java.util.*;

public class LaunchServiceProviderUnitTest {

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @DisplayName("should throw exception when pass null to LaunchServiceProvider constructor ")
    @Test
    public void shouldThrowExceptionWhenSetLaunchServiceProviderConstructorToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> new LaunchServiceProvider(null,2000,null));
        assertEquals("Name and Country cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass empty to LaunchServiceProvider constructor ")
    @Test
    public void shouldThrowExceptionWhenSetLaunchServiceProviderConstructorToEmpty() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new LaunchServiceProvider("",2000,""));
        assertEquals("Name and Country cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass zero to LaunchServiceProvider constructor for yearFounded")
    @Test()
    public void shouldThrowExceptionWhenSetLaunchServiceProviderConstructorToZero() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new LaunchServiceProvider("",-5,""));
        assertEquals("Year Founded cannot be < 0 or have < 4 digits", exception.getMessage());
    }

    @DisplayName("should return False when two objects with different attribute values are equal")
    @Test
    public void shouldReturnFalseWhenDifferentObjectsAreEqual()
    {
        LaunchServiceProvider a = new LaunchServiceProvider("abc",2000,"Australia");
        LaunchServiceProvider b = new LaunchServiceProvider("def",2000,"Australia");
        assertFalse(a.equals(b));
    }

    @DisplayName("should return True when two objects with same attribute values are equal")
    @Test
    public void shouldReturnTrueWhenObjectsAreEqual()
    {
        LaunchServiceProvider a = new LaunchServiceProvider("abc",2000,"Australia");
        LaunchServiceProvider b = new LaunchServiceProvider("abc",2000,"Australia");
        assertTrue(a.equals(b));
    }

    @DisplayName("should return True when two objects with same attribute values have equal hash codes")
    @Test
    public void shouldReturnTrueWhenObjectsHaveEqualHashes()
    {
        LaunchServiceProvider a = new LaunchServiceProvider("abc",2000,"Australia");
        LaunchServiceProvider b = new LaunchServiceProvider("abc",2000,"Australia");
        assertTrue(a.hashCode() == b.hashCode());
    }

    @DisplayName("should return False when two objects with different attribute values have equal hash codes")
    @Test
    public void shouldReturnTrueWhenObjectsHaveUnEqualHashes()
    {
        LaunchServiceProvider a = new LaunchServiceProvider("abc",2000,"Australia");
        LaunchServiceProvider b = new LaunchServiceProvider("def",2000,"Australia");
        assertFalse(a.hashCode() == b.hashCode());
    }

    @DisplayName("should return False when setHeadQuarters is wrong")
    @Test
    public void shouldReturnFalseWhenSetHeadQuartersReturnsSameValue()
    {
        LaunchServiceProvider a = new LaunchServiceProvider("abc",2000,"Australia");
        LaunchServiceProvider b = new LaunchServiceProvider("def",2000,"Australia");
        a.setHeadquarters("Australia");
        b.setHeadquarters("USA");
        assertFalse(a.getHeadquarters().equals(b.getHeadquarters()));
    }

    @DisplayName("should return True when setHeadQuarters is right")
    @Test
    public void shouldReturnTrueWhenSetHeadQuartersReturnsSameValue()
    {
        LaunchServiceProvider a = new LaunchServiceProvider("abc",2000,"Australia");
        LaunchServiceProvider b = new LaunchServiceProvider("def",2000,"Australia");
        a.setHeadquarters("Australia");
        b.setHeadquarters("Australia");
        assertTrue(a.getHeadquarters().equals(b.getHeadquarters()));
    }

    @DisplayName("should return True when two Rocket sets are equal")
    @Test
    public void shouldReturnTrueWhenSetRocketsIsTrue()
    {
        LaunchServiceProvider a = new LaunchServiceProvider("abc",2000,"Australia");
        a.setHeadquarters("Australia");
        Set<Rocket> rocketSet = new HashSet<Rocket>();
        LaunchServiceProvider manufacturer = new LaunchServiceProvider("SpaceX", 2002, "USA");
        Rocket r1 = new Rocket("aaa","USA",manufacturer);
        Rocket r2 = new Rocket("bbb","Australia",manufacturer);
        rocketSet.add(r1);
        rocketSet.add(r2);
        a.setRockets(rocketSet);
        assertTrue(a.getRockets().equals(rocketSet));
    }

    @DisplayName("should return True when setRocket is valid")
    @Test
    public void shouldReturnTrueWhenSetRocketsIsValid()
    {
        LaunchServiceProvider a = new LaunchServiceProvider("abc",2000,"Australia");
        a.setHeadquarters("Australia");
        Set<Rocket> rocketSet = new HashSet<Rocket>();
        LaunchServiceProvider manufacturer = new LaunchServiceProvider("SpaceX", 2002, "USA");
        Rocket r1 = new Rocket("Aaaaaa","USA",manufacturer);
        Rocket r2 = new Rocket("Bbbbbb","Australia",manufacturer);
        rocketSet.add(r1);
        rocketSet.add(r2);
        a.setRockets(rocketSet);
        assertTrue(a.getRockets().equals(rocketSet));
    }

    @DisplayName("should return False when setRocket is invalid")
    @Test
    public void shouldReturnFalseWhenSetRocketsIsInvalid()
    {
        LaunchServiceProvider a = new LaunchServiceProvider("abc",2000,"Australia");
        Set<Rocket> rocketSet = new HashSet<Rocket>();
        a.setRockets(rocketSet);
        assertFalse(a.getRockets().getClass().toString().equals("Rocket"));
    }

    @DisplayName("should return False when object is initialised with invalid values")
    @Test
    public void shouldReturnFalseWhenConstructorHasInvalidInput()
    {
        String name = "abc123...";
        LaunchServiceProvider a = new LaunchServiceProvider(name,2000,"Australia");
        assertFalse(name.equals(a.getName()));
    }

    @DisplayName("should return False when object is initialised with valid values")
    @Test
    public void shouldReturnFalseWhenConstructorHasValidInput()
    {
        String name = "Abcdef";
        LaunchServiceProvider a = new LaunchServiceProvider(name,2000,"Australia");
        assertTrue(name.equals(a.getName()));
    }


    @DisplayName("should return False when invalid name is set to Head Quarters")
    @Test
    public void shouldReturnFalseWhenSetHQHasInValidInput()
    {
        String name = "USA...";
        LaunchServiceProvider a = new LaunchServiceProvider(name,2000,"Australia");
        a.setHeadquarters(name);
        assertFalse(name.equals(a.getHeadquarters()));
    }

    @DisplayName("should return True when Head Quarters is set to a valid value")
    @Test
    public void shouldReturnFalseWhenSetHQHasValidInput()
    {
        String name = "ESA";
        LaunchServiceProvider a = new LaunchServiceProvider("SpaceX", 2002, "USA");
        a.setHeadquarters(name);
        assertTrue(name.equals(a.getHeadquarters()));
    }
}