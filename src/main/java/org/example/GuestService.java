package org.example;

import java.util.Scanner;

public class GuestService {

    GuestRepository repository = new GuestRepository();
    public Guest createNewGuest(String firstName, String lastName, int age, int genderOption) {
        Gender gender = Gender.LGBTQ;
       switch (genderOption){
           case 1:
               gender = Gender.FEMALE;
               break;
           case 2:
               gender = Gender.MALE;
               break;
           case 3:
               gender = Gender.LGBTQ;
               break;
       }

        return repository.createNewGuest(firstName, lastName, age, gender);
    }
}
