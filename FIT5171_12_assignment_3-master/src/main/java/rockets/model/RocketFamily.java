package rockets.model;

import java.util.Set;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.Validate.notBlank;

public class RocketFamily extends Entity{

    private String familyName;

    Set <Set<Rocket>> familyTree;


    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        notBlank(familyName,"family name cannot be null or empty");
        if (isNameValid(familyName))
            this.familyName = familyName;
    }

    public boolean isNameValid(String name)
    {
        String regex = "^[a-zA-Z\\\\s]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(name);
        return m.matches();
    }

    public Set<Set<Rocket>> getFamilyTree() {
        return familyTree;
    }

    public void setFamilyTree(Set<Set<Rocket>> familyTree)
    {
        if ( familyTree.size() > 0 )
        this.familyTree = familyTree;
    }
}