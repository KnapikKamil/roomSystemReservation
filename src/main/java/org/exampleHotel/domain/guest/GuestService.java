package org.exampleHotel.domain.guest;

import org.exampleHotel.domain.ObjectPool;
import org.exampleHotel.domain.guest.dto.GuestDTO;

import java.util.ArrayList;
import java.util.List;

public class GuestService {

   private final GuestRepository repository = ObjectPool.getGuestRepository();

    private final static GuestService instance = new GuestService();
    private GuestService() {
    }
    public static GuestService getInstance() {
        return instance;
    }




    public Guest createNewGuest(String firstName, String lastName, int age, int genderOption) {
        Gender gender;
        switch (genderOption) {
            case 1:
                gender = Gender.FEMALE;
                break;
            case 2:
                gender = Gender.MALE;
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

    public void remove(long id) {
        this.repository.remove(id);
    }

    public void edit(long id, String firstName, String lastName, int age, int genderOption) {
        Gender gender;
        switch (genderOption) {
            case 1:
                gender = Gender.FEMALE;
                break;
            case 2:
                gender = Gender.MALE;
                break;
            default:
                gender = Gender.LGBTQ;
        }

        this.repository.edit(id, firstName, lastName, age, gender);
    }

    public Guest getGuestById(long id) {
        return this.repository.findById(id);

    }
    public List<GuestDTO> getAllAsDTO(){
        List<GuestDTO> result = new ArrayList<>();
        List<Guest> allGuests = repository.getAll();

        if (allGuests != null) {
            for (Guest guest : allGuests) {
                GuestDTO guestDTO = guest.getAsDTO();
                result.add(guestDTO);
            }
        }
        return result;
    }



}
