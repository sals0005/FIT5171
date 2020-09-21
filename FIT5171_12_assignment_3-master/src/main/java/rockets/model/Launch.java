package rockets.model;

import org.neo4j.ogm.annotation.CompositeIndex;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.neo4j.ogm.annotation.Relationship.INCOMING;

@NodeEntity
@CompositeIndex(properties = {"launchDate", "launchVehicle", "launchSite", "orbit"}, unique = true)
public class Launch extends Entity {
    public enum LaunchOutcome {
        FAILED, SUCCESSFUL
    }
    @Property(name = "launchDate")
    private LocalDate launchDate;

    @Relationship(type = "PROVIDES", direction = INCOMING)
    private Rocket launchVehicle;

    private LaunchServiceProvider launchServiceProvider;

    private Set<String> payload;

    @Property(name = "launchSite")
    private String launchSite;

    @Property(name = "orbit")
    private String orbit;

    @Property(name = "function")
    private String function;

    @Property(name = "price")
    private BigDecimal price;

    @Property(name = "launchOutcome")
    private LaunchOutcome launchOutcome;

    public LocalDate getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(LocalDate launchDate) {

        if ( isValidDate(launchDate))
            this.launchDate = launchDate;
    }

    public boolean isValidDate(LocalDate date)
    {
        String dateS = date.toString();
        String dateFormat = "YYYY-mm-dd";
        if(dateS == null || date.isAfter(LocalDate.now()))
        {
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);

        try {

            Date dateNew = sdf.parse(dateS);

        } catch (ParseException e) {

            return false;
        }
        return true;
    }
    public Rocket getLaunchVehicle() {
        return launchVehicle;
    }

    public void setLaunchVehicle(Rocket launchVehicle) {
        if ((launchVehicle instanceof Rocket) && (launchVehicle != null))
            this.launchVehicle = launchVehicle;
    }

    public LaunchServiceProvider getLaunchServiceProvider() {
        return launchServiceProvider;
    }

    public void setLaunchServiceProvider(LaunchServiceProvider launchServiceProvider) {
        if ((launchServiceProvider instanceof LaunchServiceProvider) && (launchServiceProvider != null))
            this.launchServiceProvider = launchServiceProvider;
    }

    public Set<String> getPayload() {
        return this.payload;
    }

    public void setPayload(Set<String> payload) {
        if (! payload.isEmpty())
            this.payload = payload;
    }

    public String getLaunchSite() {
        return launchSite;
    }

    public void setLaunchSite(String launchSite) {
        notBlank(launchSite);
        if (isNameValid(launchSite))
        this.launchSite = launchSite;
    }

    public boolean isNameValid(String name)
    {
        String regex = "^[a-zA-Z]{4,}(?: [a-zA-Z]+){0,2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(name);
        return m.matches();
    }

    public String getOrbit() {
        return orbit;
    }

    public void setOrbit(String orbit) {

        notBlank(orbit,"orbit cannot be null or empty");
        this.orbit = orbit;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {

        notBlank(function,"function cannot be null or empty");
        this.function = function;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if ((price instanceof BigDecimal) && (price.doubleValue() > 0.0))
        this.price = price;
    }

    public LaunchOutcome getLaunchOutcome() {
        return launchOutcome;
    }

    public void setLaunchOutcome(LaunchOutcome launchOutcome) {
        if (launchOutcome instanceof  LaunchOutcome)
            this.launchOutcome = launchOutcome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Launch launch = (Launch) o;
        return Objects.equals(launchDate, launch.launchDate) &&
                Objects.equals(launchVehicle, launch.launchVehicle) &&
                Objects.equals(launchServiceProvider, launch.launchServiceProvider) &&
                Objects.equals(orbit, launch.orbit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(launchDate, launchVehicle, launchServiceProvider, orbit);
    }
}
