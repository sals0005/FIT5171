package rockets.model;

import org.apache.commons.lang3.Validate;

import java.util.Objects;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

public class Rocket extends Entity {
    private String name;

    private String country;

    private LaunchServiceProvider manufacturer;

    private String massToLEO;

    private String massToGTO;

    private String massToOther;

    /**
     * All parameters shouldn't be null.
     *
     * @param name
     * @param country
     * @param manufacturer
     */
    public Rocket(String name, String country, LaunchServiceProvider manufacturer) {
        //validate the Rocket name,country,manufacturer can not be null or empty
        notBlank(name,"Rocket name cannot be null or empty");
        notBlank(country,"Rocket country cannot be null or empty");
        notNull(manufacturer,"Rocket manufacturer cannot be null");

        this.name = name;
        this.country = country;
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public LaunchServiceProvider getManufacturer() {
        return manufacturer;
    }

    public String getMassToLEO() {
        return massToLEO;
    }

    public String getMassToGTO() {
        return massToGTO;
    }

    public String getMassToOther() {
        return massToOther;
    }

    public void setMassToLEO(String massToLEO) {
        //validate the mass to leo can not be null
        notNull(massToLEO, "mass to leo cannot be null");
        //validate the mass to leo should be a integer greater than 0
        Util.isValidMass(massToLEO);
        this.massToLEO = massToLEO;
    }

    public void setMassToGTO(String massToGTO) {
        //validate the mass to gto can not be null
        notNull(massToGTO, "mass to gto cannot be null");
        //validate the mass to leo should be a integer greater than 0
        Util.isValidMass(massToGTO);
        this.massToGTO = massToGTO;
    }

    public void setMassToOther(String massToOther) {
        //validate the mass to other can not be null
        notNull(massToOther, "mass to other cannot be null");
        //validate the mass to other should be a integer greater than 0
        Util.isValidMass(massToOther);
        this.massToOther = massToOther;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rocket rocket = (Rocket) o;
        return Objects.equals(name, rocket.name) &&
                Objects.equals(country, rocket.country) &&
                Objects.equals(manufacturer, rocket.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country, manufacturer);
    }

    @Override
    public String toString() {
        return "Rocket{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", massToLEO='" + massToLEO + '\'' +
                ", massToGTO='" + massToGTO + '\'' +
                ", massToOther='" + massToOther + '\'' +
                '}';
    }
}
