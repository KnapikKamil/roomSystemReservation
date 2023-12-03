package org.exampleHotel.ui.gui.reservations;

public class GuestSelectionItem {

    private String firstName;
    private String lastName;
    private long id;

    public GuestSelectionItem(String firstName, String lastName, long id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public long getId() {
        return id;
    }
    public String toString(){
        return String.format("%s, %s", this.firstName, this.lastName);
    }
}
