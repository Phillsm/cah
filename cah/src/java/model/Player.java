package model;

/**
 *
 * @author Fisk
 */
public class Player {

    public Player(String studentID, String name, String address) {
        this.studentID = studentID;
        this.name = name;
        this.address = address;
    }
    
    private String studentID;
    private String name;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

}
