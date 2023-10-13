package org.exampleHotel.domain.guest;


import org.exampleHotel.exceptions.PersistenceToFileException;
import org.exampleHotel.util.Properties;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GuestRepository {

    private final List<Guest> guests = new ArrayList<>();

    Guest createNewGuest(String firstName, String lastName, int age, Gender gender) {
        Guest newGuest = new Guest(findNewId(), firstName, lastName, age, gender);
        guests.add(newGuest);
        return newGuest;
    }
    Guest addGuestFromFile(int id, String firstName, String lastName, int age, Gender gender) {
        Guest newGuest = new Guest(id, firstName, lastName, age, gender);
        guests.add(newGuest);
        return newGuest;
    }

    public List<Guest> getAll() {
        return this.guests;
    }

    void saveAll() {
        String name = "guests.csv";
        Path file = Paths.get(Properties.DATA_DIRECTORY.toString(), name);
        StringBuilder stringBuilder = new StringBuilder("");
        for (Guest guest : guests) {
            stringBuilder.append(guest.toCSV());
        }

        try {
            Files.writeString(file, stringBuilder.toString(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new PersistenceToFileException(file.toString(), "write", "guest data");
        }
    }

    void readAll() {
        String name = "guests.csv";
        Path file = Paths.get(Properties.DATA_DIRECTORY.toString(), name);

        if (!Files.exists(file)){
            return;
        }

        try {
            String data = Files.readString(file, StandardCharsets.UTF_8);
            String[] guestsAsString = data.split(System.getProperty("line.separator"));
            for(String guestAsString : guestsAsString) {
                String[] guestData = guestAsString.split(",");
                int id = Integer.parseInt(guestData[0]);
                int age = Integer.parseInt(guestData[3]);
                Gender gender = Gender.valueOf(guestData[4]);
                addGuestFromFile(id, guestData[1], guestData[2], age, gender);
            }
        } catch (IOException e) {
            throw new PersistenceToFileException(file.toString(), "read", "guest data");
        }
    }
    private int findNewId(){
        int max = 0;
        for (Guest guest : this.guests){
            if (guest.getId()>max){
                max = guest.getId();
            }
        }
        return max+1;
    }

    public void remove(int id) {
        int guestToBeRemovedIndex = -1;
        for (int i = 0; i < guests.size(); i++){
            if (this.guests.get(id).getId() == id){
                guestToBeRemovedIndex = i;
                break;
            }
            if (guestToBeRemovedIndex> -1){
                this.guests.remove(guestToBeRemovedIndex);

            }
        }
    }

    public void edit(int id, String firstName, String lastName, int age, Gender gender) {
        this.remove(id);
        this.addGuestFromFile(id, firstName, lastName, age,gender);
    }
}