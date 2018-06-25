/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p6db_custscreensrude;

/**
 *
 * @author Joe
 */
public class Customer {
    private int ID;
    private String firstName;
    private String lastName;
    private String phone;

    public Customer(int ID, String fName, String lName, String ph) {
        this.ID = ID;
        this.firstName = fName;
        this.lastName = lName;
        this.phone = ph;
    }
    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    @Override
    public String toString() {
        return ID + " " + firstName + " " + lastName + " "
                + phone;
    }
}

