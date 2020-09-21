package rockets.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RocketParentTest {

    private RocketParent target;

    @BeforeEach
    public void setUp() {
        target = new RocketParent();
    }

    @DisplayName("should throw exception when pass a empty name to setParentName function")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    public void shouldThrowExceptionWhenSetFirstNameToEmpty(String parentName) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setParentName(parentName));
        assertEquals("parent name cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass null to setParentName function")
    @Test
    public void shouldThrowExceptionWhenSetFirstNameToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.setParentName(null));
        assertEquals("parent name cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should return True when setRocket is valid")
    @Test
    public void shouldReturnTrueWhenSetRocketIsValid()
    {
        RocketParent a = new RocketParent();
        Set<Rocket> rocketSet1 = new HashSet<Rocket>();
        LaunchServiceProvider manufacturer = new LaunchServiceProvider("SpaceX", 2002, "USA");
        Rocket r1 = new Rocket("Aaaaaa","USA",manufacturer);
        Rocket r2 = new Rocket("Bbbbbb","Australia",manufacturer);
        rocketSet1.add(r1);
        rocketSet1.add(r2);
        a.setRockets(rocketSet1);
        assertTrue(a.getRockets().equals(rocketSet1));
    }

    @DisplayName("should return False when setRocket is invalid")
    @Test
    public void shouldReturnFalseWhenSetRocketIsInvalid()
    {
        RocketParent a = new RocketParent();
        Set<Rocket> family = new HashSet<Rocket>();
        a.setRockets(family);
        assertFalse(family.equals(a.getRockets()));
    }

    @DisplayName("should return False when setParentName is invalid")
    @Test
    public void shouldReturnFalseWhenSetParentNameIsInvalid()
    {
        RocketParent a = new RocketParent();
        a.setParentName("abc123...");
        assertFalse("abc123...".equals(a.getParentName()));
    }

    @DisplayName("should return True when setParentName is valid")
    @Test
    public void shouldReturnTrueWhenSetParentNameIsValid()
    {
        RocketParent a = new RocketParent();
        a.setParentName("ABCaaa");
        assertTrue("ABCaaa".equals(a.getParentName()));
    }

}