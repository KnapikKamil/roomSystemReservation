package org.exampleHotel.domain.guest;


import org.exampleHotel.domain.guest.Gender;
import org.exampleHotel.domain.guest.Guest;

import java.awt.image.ImagingOpException;
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
        Guest newGuest = new Guest(firstName, lastName, age, gender);
        guests.add(newGuest);
        return newGuest;
    }

    public List<Guest> getAll() {
        return this.guests;
    }

    void saveAll() {
        String name = "guests.csv";
        Path file = Paths.get(System.getProperty("user.home"), "reservation_system", name);
        StringBuilder stringBuilder = new StringBuilder("");
        for (Guest guest : guests) {
            stringBuilder.append(guest.toCSV());
        }

        try {
            Path reservation_system_dir = Paths.get(System.getProperty("user.home"), "reservation_system");
            if (!Files.isDirectory(reservation_system_dir)) {
                Files.createDirectory(reservation_system_dir);
            }
            Files.writeString(file, stringBuilder.toString(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void readAll() {
        String name = "guests.csv";
        Path file = Paths.get(
                System.getProperty("user.home"),
                "reservation_system",
                name);
        try {
            String data = Files.readString(file, StandardCharsets.UTF_8);
            String[] guestsAsString = data.split(System.getProperty("line.separator"));
            for(String guestAsString : guestsAsString) {
                String[] guestData = guestAsString.split(",");
                int age = Integer.parseInt(guestData[2]);
                Gender gender = Gender.valueOf(guestData[3]);
                createNewGuest(guestData[0],guestData[1],age,gender);
            }
        } catch (IOException e) {
            System.out.println("Nie udało się odczytać pliku z danymi");
            e.printStackTrace();
        }
    }
}
