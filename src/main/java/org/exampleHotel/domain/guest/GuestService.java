package org.exampleHotel.domain.guest;

import org.exampleHotel.domain.guest.Gender;
import org.exampleHotel.domain.guest.Guest;
import org.exampleHotel.domain.guest.GuestRepository;

import java.util.List;

public class GuestService {

    GuestRepository repository = new GuestRepository();
    public Guest createNewGuest(String firstName, String lastName, int age, int genderOption) {
        Gender gender;
        switch (genderOption) {
            case 1 -> gender = Gender.FEMALE;
            case 2 -> gender = Gender.MALE;
            case 3 -> gender = Gender.LGBTQ;
            default ->  gender = Gender.LGBTQ;
        }

        return repository.createNewGuest(firstName, lastName, age, gender);
    }

    public  List<Guest> getAllGuests() {

        return this.repository.getAll();
    }
    public void saveAll(){
        this.repository.saveAll();
    }
  public void readAll() {
      this.repository.readAll();
  }

    public void removeGuest(int id) {
        this.repository.remove(id);
    }

    public void editGuest(int id, String firstName, String lastName, int age, int genderOption) {

        Gender gender;
        switch (genderOption) {
            case 1 -> gender = Gender.FEMALE;
            case 2 -> gender = Gender.MALE;
            case 3 -> gender = Gender.LGBTQ;
            default ->  gender = Gender.LGBTQ;
        }


        repository.edit(id, firstName, lastName, age, gender);
    }
}
