package rockets.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RocketFamilyUnitTest {

    private RocketFamily target;

    @BeforeEach
    public void setUp() {
        target = new RocketFamily();
    }

    @DisplayName("should throw exception when pass a empty name to setFamilyName function")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    public void shouldThrowExceptionWhenSetFirstNameToEmpty(String familyName) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setFamilyName(familyName));
        assertEquals("family name cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass null to setFamilyName function")
    @Test
    public void shouldThrowExceptionWhenSetFirstNameToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.setFamilyName(null));
        assertEquals("family name cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should return True when setFamilyTree is valid")
    @Test
    public void shouldReturnTrueWhenSetFamilyIsValid()
    {
        RocketFamily a = new RocketFamily();
        Set<Rocket> rocketSet1 = new HashSet<Rocket>();
        Set<Rocket> rocketSet2 = new HashSet<Rocket>();
        Set<Set<Rocket>> family = new HashSet<Set<Rocket>>();
        LaunchServiceProvider manufacturer = new LaunchServiceProvider("SpaceX", 2002, "USA");
        Rocket r1 = new Rocket("Aaaaaa","USA",manufacturer);
        Rocket r2 = new Rocket("Bbbbbb","Australia",manufacturer);
        rocketSet1.add(r1);
        rocketSet2.add(r2);
        family.add(rocketSet1);
        family.add(rocketSet2);
        a.setFamilyTree(family);
        assertTrue(a.getFamilyTree().equals(family));
    }

    @DisplayName("should return False when setFamilyTree is invalid")
    @Test
    public void shouldReturnFalseWhenSetFamilyTreeIsInvalid()
    {
        RocketFamily a = new RocketFamily();
        Set<Set<Rocket>> family = new HashSet<Set<Rocket>>();
        a.setFamilyTree(family);
        assertFalse(family.equals(a.getFamilyTree()));
    }

    @DisplayName("should return False when setFamilyName is invalid")
    @Test
    public void shouldReturnFalseWhenSetFamilyNameIsInvalid()
    {
        RocketFamily a = new RocketFamily();
        a.setFamilyName("abc123...");
        assertFalse("abc123...".equals(a.getFamilyName()));
    }

    @DisplayName("should return True when setFamilyName is valid")
    @Test
    public void shouldReturnTrueWhenSetFamilyNameIsValid()
    {
        RocketFamily a = new RocketFamily();
        a.setFamilyName("ABCaaa");
        assertTrue("ABCaaa".equals(a.getFamilyName()));
    }
}