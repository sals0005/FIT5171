package rockets.model;

import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Objects;


import  java.util.regex.*;

import static org.apache.commons.lang3.Validate.notBlank;

@NodeEntity
public class User extends Entity {
    private String firstName;

    private String lastName;

    private String email;

    private String password;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        //Changes from assignment 1
        notBlank(firstName,"first name cannot be null or empty");
        if (isNameValid(firstName))
            this.firstName = firstName;

    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        //changes from assignment 1
        notBlank(lastName,"last name cannot be null or empty");
        if (isNameValid(lastName))
            this.lastName = lastName;

    }
    public boolean isNameValid(String name)
    {
        String regex = "[A-Z][a-z]*";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(name);
        return m.matches();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        notBlank(email, "email cannot be null or empty");
        if (isValidEmailId(email)) {
            this.email = email;
        }
    }

    public boolean isValidEmailId(String email)
    {

        String regex = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(email);
        return m.matches();

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        //Change made new - tuesday
        notBlank(password,"password cannot be null or empty");
        if( isValidPassword(password))
            this.password = password;
    }

    public boolean isValidPassword(String password)
    {

        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(password);
        return m.matches();

    }

    // match the given password against user's password and return the result
    public boolean isPasswordMatch(String password) {
        return this.password.equals(password.trim());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
