package rockets.model;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

public class RocketFamily extends Entity {
    private String variation;

    private String subVariation;
    private String family;
    private String payloadToGTOCapacity;
    private String payloadToLEOCapacity;

    public RocketFamily(String family) {
        notBlank(family,"family name cannot be null or empty");
        this.family = family;
    }

    public String getVariation() {
        return variation;
    }

    public void setVariation(String variation) {
        //validate the variation can not be null or empty
        notBlank(variation, "variation cannot be null or empty") ;
        //validate the Variation must has it's family name
        Util.isValidVariation(family, variation);
        this.variation = variation;
    }

    public String getSubvariation() {
        return subVariation;
    }

    public void setSubVariation(String subVariation) {
        //validate the subVariation can not be null, but can be empty
        notNull(subVariation, "subVariation cannot be null");
        this.subVariation = subVariation;
    }

    public String getFamily() {
        return family;
    }


    public String getPayloadToGTOCapacity() {
        return payloadToGTOCapacity;
    }

    public void setPayloadToGTOCapacity(String payloadToGTOCapacity) {
        notNull(payloadToGTOCapacity,"mass to gto cannot be null");
        Util.isValidMass(payloadToGTOCapacity);
        this.payloadToGTOCapacity = payloadToGTOCapacity;
    }

    public String getPayloadToLEOCapacity() {
        return payloadToLEOCapacity;
    }

    public void setPayloadToLEOCapacity(String payloadToLEOCapacity) {

        notNull(payloadToLEOCapacity,"mass to leo cannot be null");
        Util.isValidMass(payloadToLEOCapacity);
        this.payloadToLEOCapacity = payloadToLEOCapacity;
    }
}
