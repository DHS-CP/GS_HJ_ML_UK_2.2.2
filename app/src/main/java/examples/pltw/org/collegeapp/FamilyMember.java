package examples.pltw.org.collegeapp;

/**
 * Created by ckinnard on 4/3/2017.
 */
public abstract class FamilyMember extends ApplicantData {
    private String firstName;
    private String lastName;

    public FamilyMember() {//default boring ness
        firstName = "Ada";
        lastName = "Lovelace";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
