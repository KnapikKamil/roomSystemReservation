package org.exampleHotel.domain.guest;

import org.exampleHotel.domain.guest.Gender;
import org.exampleHotel.domain.guest.Guest;
import org.exampleHotel.domain.guest.GuestRepository;

import java.util.List;

public class GuestService {

    GuestRepository repository = new GuestRepository();
    public Guest createNewGuest(String firstName, String lastName, int age, int genderOption) {
        Gender gender = Gender.LGBTQ;
        switch (genderOption) {
            case 1 -> gender = Gender.FEMALE;
            case 2 -> gender = Gender.MALE;
            case 3 -> gender = Gender.LGBTQ;
        }

        return repository.createNewGuest(firstName, lastName, age, gender);
    }

    public  List<Guest> getAllGuests() {

        return this.repository.getAll();
    }
}
