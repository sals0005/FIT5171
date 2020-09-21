package rockets.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class UserUnitTest {
    private User target;

    @BeforeEach
    public void setUp() {
        target = new User();
    }


    @DisplayName("should throw exception when pass a empty first name to setFirstName function")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    public void shouldThrowExceptionWhenSetFirstNameToEmpty(String firstName) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setFirstName(firstName));
        assertEquals("first name cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass null to setFirstName function")
    @Test
    public void shouldThrowExceptionWhenSetFirstNameToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.setFirstName(null));
        assertEquals("first name cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass a empty last name to setLastName function")
    @ParameterizedTest
    @ValueSource(strings = {"   ", " ", "  "})
    public void shouldThrowExceptionWhenSetLastNameToEmpty(String lastName) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setLastName(lastName));
        assertEquals("last name cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass null to setLastName function")
    @Test
    public void shouldThrowExceptionWhenSetLastNameToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.setLastName(null));
        assertEquals("last name cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should return False when the name entered is invalid. Ex : abc123 is valid ")
    @Test
    public void shouldReturnFalseWhenUserEntersInValidName() {
        String name = "abc123";
        target.setFirstName(name);
        assertFalse(name.equals(target.getFirstName()));
    }

    @DisplayName("should return True when the name entered is valid. Ex : abc is valid ")
    @Test
    public void shouldReturnTrueWhenUserEntersValidName() {
        String name = "Abc";
        target.setFirstName(name);
        assertTrue(target.getFirstName().equals(name));
    }

    @DisplayName("should throw exception when pass a empty email address to setEmail function")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    public void shouldThrowExceptionWhenSetEmailToEmpty(String email) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setEmail(email));
        assertEquals("email cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass null to setEmail function")
    @Test
    public void shouldThrowExceptionWhenSetEmailToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.setEmail(null));
        assertEquals("email cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exception when pass a empty password to setPassword function")
    @ParameterizedTest
    @ValueSource(strings = {"  ", " ", "  "})
    public void shouldThrowExceptionWhenSetPasswordToEmpty(String passWord) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setPassword(passWord));
        assertEquals("password cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should throw exceptions when pass a null password to setPassword function")
    @Test
    public void shouldThrowExceptionWhenSetPasswordToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.setPassword(null));
        assertEquals("password cannot be null or empty", exception.getMessage());
    }

    @DisplayName("should return true when two users have the same email")
    @Test
    public void shouldReturnTrueWhenUsersHaveSameEmail() {
        String email = "abc@example.com";
        target.setEmail(email);
        User anotherUser = new User();
        anotherUser.setEmail(email);
        assertTrue(target.equals(anotherUser));
    }

    @DisplayName("should return True when the email entered is valid. Ex : abc@example.com is valid ")
    @Test
    public void shouldReturnTrueWhenUserEntersValidEmail() {
        String email = "abc@example.com";
        assertTrue(target.isValidEmailId(email));
    }

    @DisplayName("should return False when the email entered is not valid. Ex : abc@examplecom is valid ")
    @Test
    public void shouldReturnFalseWhenUserEntersInValidEmail() {
        String email = "abc@examplecom";
        assertFalse(target.isValidEmailId(email));
    }

    @DisplayName("should return True when the Password entered is valid. Ex : Aa1&abcd is valid ")
    @Test
    public void shouldReturnTrueWhenUserEntersValidPassword() {
        String passWord = "Aa1&abcd";
        assertTrue(target.isValidPassword(passWord));
    }

    @DisplayName("should return False when the Password entered is not valid. Ex : Aab&abcd is valid ")
    @Test
    public void shouldReturnFalseWhenUserEntersInValidPassword() {
        String passWord = "Aab&abcd";
        assertFalse(target.isValidPassword(passWord));
    }

    @DisplayName("should return false when two users have different emails")
    @Test
    public void shouldReturnFalseWhenUsersHaveDifferentEmails() {
        target.setEmail("abc@example.com");
        User anotherUser = new User();
        anotherUser.setEmail("def@example.com");
        assertFalse(target.equals(anotherUser));
    }

    @DisplayName("should return false when two hash codes of different email have different output")
    @Test
    public void shouldReturnFalseWhenDifferentEmailHaveDifferentHashes() {
        target.setEmail("abc@example.com");
        User anotherUser = new User();
        anotherUser.setEmail("def@example.com");
        assertFalse(target.hashCode() == anotherUser.hashCode());
    }

    @DisplayName("should return True when two hash codes of same email have same outputs")
    @Test
    public void shouldReturnTrueWhenSameEmailHaveSameHashes() {
        target.setEmail("abc@example.com");
        User anotherUser = new User();
        anotherUser.setEmail("abc@example.com");
        assertTrue(target.hashCode() == anotherUser.hashCode());
    }

    @DisplayName("should return false when two Objects are not equal")
    @Test
    public void shouldReturnFalseWhenObjectsAreNotEqual() {
        target.setEmail("abc@example.com");
        User anotherUser = new User();
        anotherUser.setEmail("def@example.com");
        assertFalse(target.equals(anotherUser));
    }

    @DisplayName("should return True when two Objects are equal")
    @Test
    public void shouldReturnTrueWhenObjectsAreEqual() {
        target.setEmail("abc@example.com");
        User anotherUser = new User();
        anotherUser.setEmail("abc@example.com");
        assertTrue(target.equals(anotherUser));
    }

    @DisplayName("should return True when two Objects with same attribute values are converted to string ")
    @Test
    public void shouldReturnTrueWhenToStringOutputsAreSame() {
        target.setEmail("abc@example.com");
        target.setFirstName("abc");
        target.setLastName("def");
        User anotherUser = new User();
        anotherUser.setEmail("abc@example.com");
        anotherUser.setFirstName("abc");
        anotherUser.setLastName("def");
        assertTrue(target.toString().equals(anotherUser.toString()));
    }

    @DisplayName("should return False when two Objects with different attribute values are converted to string ")
    @Test
    public void shouldReturnFalseWhenToStringOutputsAreNotSame() {
        target.setEmail("abc@example.com");
        target.setFirstName("abc");
        target.setLastName("def");
        User anotherUser = new User();
        anotherUser.setEmail("def@example.com");
        anotherUser.setFirstName("abcABC");
        anotherUser.setLastName("defDEF");
        assertFalse(target.toString().equals(anotherUser.toString()));
    }

    @DisplayName("should return true when passwords have a match")
    @Test
    public void shouldReturnTrueWhenPasswordMatches() {
        target.setEmail("abc@example.com");
        target.setFirstName("abc");
        target.setLastName("def");
        target.setPassword("Aa1&abcd");
        assertTrue(target.getPassword().equals("Aa1&abcd"));
    }

    @DisplayName("should return false when passwords do not have a match")
    @Test
    public void shouldReturnFalseWhenPasswordDoNotMatch() {
        target.setEmail("abc@example.com");
        target.setFirstName("abc");
        target.setLastName("def");
        target.setPassword("Aa1&abcd");
        assertFalse(target.getPassword().equals("Rr1.ABDEF"));
    }

}