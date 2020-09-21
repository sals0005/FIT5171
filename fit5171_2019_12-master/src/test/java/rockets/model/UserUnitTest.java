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


    //unit test that checks the email can not be set to empty
    @DisplayName("should throw exception when pass a empty email address to setEmail function")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    public void shouldThrowExceptionWhenSetEmailToEmpty(String email) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setEmail(email));
        assertEquals("email cannot be null or empty", exception.getMessage());
    }

    //unit test that checks the email can not be set to null
    @DisplayName("should throw exception when pass null to setEmail function")
    @Test
    public void shouldThrowExceptionWhenSetEmailToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> target.setEmail(null));
        assertEquals("email cannot be null or empty", exception.getMessage());
    }

    //unit test that checks if a email is valid
    @DisplayName("should be a valid email")
    @ParameterizedTest
    @ValueSource(strings = {"abc", "abc#.com"})
    public void shouldThrowExceptionWhenEmailIsNotValid(String email) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setEmail(email));
        assertEquals("email format is wrong", exception.getMessage());
    }


    //unit test that checks the password can not be set to empty
    @DisplayName("should throw exception when pass a empty password to setPassword function")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    public void shouldThrowExceptionWhenSetPasswordToEmpty(String password) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setPassword(password));
        assertEquals("password cannot be null or empty", exception.getMessage());
    }

    //unit test that checks the password can not be set to null
    @DisplayName("should throw exceptions when pass a null password to setPassword function")
    @Test
    public void shouldThrowExceptionWhenSetPasswordToNull() {
        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> target.setPassword(null));
        assertEquals("password cannot be null or empty", exception.getMessage());
    }

    //unit test that checks if a password is valid
    @DisplayName("should be a valid password")
    @ParameterizedTest
    @ValueSource(strings = {"abc", "happyworld", "Happy"})
    public void shouldThrowExceptionWhenPasswordIsNotValid(String password) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> target.setPassword(password));
        assertEquals("Password should not be shorter than 8, and must include at least one Capital letter", exception.getMessage());
    }

    //unit test that checks the first name and last name can not be set to empty
    @DisplayName("should throw exception when pass a empty name ")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    public void shouldThrowExceptionWhenSetNameToEmpty(String name) {
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, () -> target.setFirstName(name));
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () -> target.setLastName(name));
        assertEquals("first name cannot be null or empty", exception1.getMessage());
        assertEquals("last name cannot be null or empty", exception2.getMessage());
    }

    //unit test that checks the first name and last name can not be set to null
    @DisplayName("should throw exception when pass null name")
    @Test
    public void shouldThrowExceptionWhenSetNameToNull() {
        NullPointerException exception1 = assertThrows(NullPointerException.class, () -> target.setFirstName(null));
        NullPointerException exception2 = assertThrows(NullPointerException.class, () -> target.setLastName(null));
        assertEquals("first name cannot be null or empty", exception1.getMessage());
        assertEquals("last name cannot be null or empty", exception2.getMessage());
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

    @DisplayName("should return false when two users have different emails")
    @Test
    public void shouldReturnFalseWhenUsersHaveDifferentEmails() {
        target.setEmail("abc@example.com");
        User anotherUser = new User();
        anotherUser.setEmail("def@example.com");
        assertFalse(target.equals(anotherUser));
    }

}

