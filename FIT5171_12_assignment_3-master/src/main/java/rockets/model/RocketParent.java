package rockets.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RocketParent extends RocketFamily{

    private String parentName;

    private Set<Rocket> rocketChild;


    public String getParentName()
    {
        return this.parentName;
    }

    public void setParentName(String rocketParentName)
    {
        notBlank(rocketParentName,"parent name cannot be null or empty");
        if (isNameValid(rocketParentName))
           this.parentName = rocketParentName;
    }

    public boolean isNameValid(String name)
    {
        String regex = "^[a-zA-Z\\\\s]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(name);
        return m.matches();
    }

    public Set<Rocket> getRockets() {
        return rocketChild;
    }

    public void setRockets(Set<Rocket> rockets) {

        if ( rockets.size() > 0 )
            this.rocketChild = rockets;
    }

    public void displayChildNames()
    {
        Iterator<Rocket> itr = rocketChild.iterator();
        while( itr.hasNext())
        {
            System.out.println(itr.next().getName());
        }
    }

}


