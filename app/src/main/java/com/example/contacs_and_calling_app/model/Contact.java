/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @author Nicolás Penagos Montoya
 * nicolas.penagosm98@gmail.com
 **~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package com.example.contacs_and_calling_app.model;

/*
* This class will represent each contact of a user
 */
public class Contact {

    // -------------------------------------
    // Atributtes
    // -------------------------------------
    private String id;
    private String phoneNumber;
    private String name;

    // -------------------------------------
    // Constructors
    // -------------------------------------
    public Contact(){

    }

    public Contact(String id, String phoneNumber, String name) {

        this.id = id;
        this.phoneNumber = phoneNumber;
        this.name = name;

    }

    // -------------------------------------
    // Getters and setters
    // -------------------------------------
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
