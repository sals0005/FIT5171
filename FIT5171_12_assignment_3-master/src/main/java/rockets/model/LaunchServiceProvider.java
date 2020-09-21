package rockets.model;

import com.google.common.collect.Sets;

import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.Validate.notBlank;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.CompositeIndex;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.LinkedHashSet;

import static org.apache.commons.lang3.Validate.notNull;
import static org.neo4j.ogm.annotation.Relationship.OUTGOING;

@NodeEntity
@CompositeIndex(properties = {"name", "yearFounded", "country"}, unique = true)
public class LaunchServiceProvider extends Entity {
    @Property(name = "name")
    private String name;

    @Property(name = "yearFounded")
    private int yearFounded;

    @Property(name = "country")
    private String country;

    @Property(name = "headquarters")
    private String headquarters;

    @Relationship(type = "MANUFACTURES", direction= OUTGOING)
    @JsonIgnore
    private Set<Rocket> rockets;

    public LaunchServiceProvider() {
        super();
    }


    public LaunchServiceProvider(String name, int yearFounded, String country) {
        if (! (yearFounded > 0 && Integer.toString(yearFounded).length() ==4) )
            throw new IllegalArgumentException("Year Founded cannot be < 0 or have < 4 digits");

        notBlank(name, "Name and Country cannot be null or empty");
        notBlank(country, "Name and Country cannot be null or empty");

        if ( isNameValid(name) && isNameValid(country)) {
            this.name = name;
            this.yearFounded = yearFounded;
            this.country = country;
        }
        rockets = Sets.newLinkedHashSet();

    }

    public boolean isNameValid(String name)
    {
        String regex = "^[a-zA-Z\\\\s]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(name);
        return m.matches();
    }

    public String getName() {
        return name;
    }

    public int getYearFounded() {
        return yearFounded;
    }

    public String getCountry() {
        return country;
    }

    public String getHeadquarters() {
        return headquarters;
    }

    public Set<Rocket> getRockets() {
        return rockets;
    }

    public void setHeadquarters(String headquarters) {
        notBlank(headquarters,"head quarters cannot be null or empty");
        if ( isNameValid(headquarters))
            this.headquarters = headquarters;
    }

    public void setRockets(Set<Rocket> rockets) {
        if (rockets.size() > 0 && rockets != null)
        this.rockets = rockets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LaunchServiceProvider that = (LaunchServiceProvider) o;
        return yearFounded == that.yearFounded &&
                Objects.equals(name, that.name) &&
                Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, yearFounded, country);
    }
}
