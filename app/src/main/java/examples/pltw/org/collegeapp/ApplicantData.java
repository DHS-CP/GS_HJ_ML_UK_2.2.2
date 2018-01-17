package examples.pltw.org.collegeapp;

/**
 * Created by Gustavo Silvera on 1/16/2018.
 */

//step 28 implemented by creating this class
public abstract class ApplicantData {
    private String email;
    private String objectId;

    //step 29 implemented in lines 12-18
    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}