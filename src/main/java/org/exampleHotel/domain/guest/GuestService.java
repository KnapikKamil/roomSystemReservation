package org.exampleHotel.domain.guest;

import org.exampleHotel.domain.guest.dto.GuestDTO;

import java.util.ArrayList;
import java.util.List;

public class GuestService {

   private final static GuestRepository repository = new GuestRepository();

    public Guest createNewGuest(String firstName, String lastName, int age, int genderOption) {
        Gender gender;
        switch (genderOption) {
            case 1:
                gender = Gender.FEMALE;
                break;
            case 2:
                gender = Gender.MALE;
                break;
            case 3:
                gender = Gender.LGBTQ;
                break;
            default:
                gender = Gender.LGBTQ;
        }
        return repository.createNewGuest(firstName, lastName, age, gender);
    }

    public List<Guest> getAllGuests() {

        return this.repository.getAll();
    }

    public void saveAll() {
        this.repository.saveAll();
    }

    public void readAll() {
        this.repository.readAll();
    }

    public void remove(int id) {
        this.repository.remove(id);
    }

    public void edit(int id, String firstName, String lastName, int age, int genderOption) {
        Gender gender;
        switch (genderOption) {
            case 1:
                gender = Gender.FEMALE;
                break;
            case 2:
                gender = Gender.MALE;
                break;
            case 3:
                gender = Gender.LGBTQ;
                break;
            default:
                gender = Gender.LGBTQ;
        }

        this.repository.edit(id, firstName, lastName, age, gender);
    }

    public Guest getGuestById(int id) {
        return this.repository.findById(id);

    }
    public List<GuestDTO> getAllAsDTO(){
        List<GuestDTO> result = new ArrayList<>();
        List<Guest> allGuests = repository.getAll();
        for (Guest guest : allGuests){
            GuestDTO guestDTO = guest.getAsDTO();
            result.add(guestDTO);
        }
        return result;
    }
}
