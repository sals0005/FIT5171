package rockets.model;

import java.util.Objects;
import static org.apache.commons.lang3.Validate.notNull;
import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNaN;

public class Payload extends Entity{

    private String type;

    private Double weight;

    private String mission;

    private String description;

    public Payload(String type, double weight, String mission) {

        notBlank(notNull(type));
        notNaN(weight);
        notBlank(notNull(mission));


        this.type = type;
        this.weight = weight;
        this.mission = mission;
    }

    public String getType() {

        return type;
    }

    public Double getWeight() {

        return weight;
    }

    public String getMission() {

        return mission;
    }

    public String getDescription() {

        return description;
    }

    public void setType(String type) {

        notNull(type, "type cannot be null or empty");
        notBlank(type, "type cannot be null or empty");
        this.type = type;
    }

    public void setWeight(String strWeight) {

        try {
            Double newWeight = Double.parseDouble(strWeight);
            if (newWeight < 0)
                throw new IndexOutOfBoundsException("weight cannot be a negative number");
            newWeight = weight;
        } catch (NumberFormatException ex1) {
            throw new NumberFormatException("weight has to be a number and cannot be empty");
        } catch (NullPointerException ex2) {
            throw new NullPointerException("weight cannot be null");
        }

    }

    public void setMission(String mission) {

        notNull(mission, "mission cannot be null or empty");
        notBlank(mission, "mission cannot be null or empty");
        this.mission = mission;
    }

    public void setDescription(String description) {

        this.description = description;
    }


}
