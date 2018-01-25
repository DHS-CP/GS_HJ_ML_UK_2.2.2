package examples.pltw.org.collegeapp;
import java.util.ArrayList;

/**
 * Created by Gustavo Silvera on 1/24/2018.
 */

public class Family {
    private static final String TAG = Family.class.getName();
    private ArrayList<FamilyMember> family;
    private static Family sFamily;

    private Family() {
        family = new ArrayList();
        family.add(new Guardian("Mr", "Brown"));
        family.add(new Guardian("Elton", "John"));
        family.add(new Guardian("George", "Michael"));
        family.add(new Guardian("Phil", "Colins"));
        family.add(new Guardian("Mr", "senor"));
        family.add(new Guardian());
    }

    public static Family get() {
        if (sFamily == null) {
            Family f = new Family();
            sFamily = f;
        }
        return sFamily;
    }

    public ArrayList<FamilyMember> getFamily() {
        return family;
    }

    public void setFamily(ArrayList<FamilyMember> family) {
        this.family = family;
    }
}
