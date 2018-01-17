package examples.pltw.org.collegeapp;

import java.util.Date;

/**
 * Created by wdumas on 4/11/16.
 */
public class Profile extends ApplicantData{
    private String firstName;//step 18
    private String lastName;
    private Date dateOfBirth;
    //private String email;//step 19
    //private String objectId;

    public Profile() {
        firstName = "Alan";
        lastName = "Turing";
        dateOfBirth = new Date(84, 6, 22, 0, 0);
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    //step 18 implemented in lines 21-27 below
  /*  public String getObjectId() {
        return objectId;
    }
    //step 19 implemented in lines 27-33 below
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }*/
}
