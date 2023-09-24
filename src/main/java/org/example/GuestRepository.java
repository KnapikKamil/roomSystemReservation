package org.example;


public class GuestRepository {

    public Guest createNewGuest(String firstName, String lastName, int age, Gender gender) {
        Guest newGuest = new Guest(firstName, lastName, age, gender);
        System.out.println(newGuest.getInfo());
        return newGuest;

    }
}
