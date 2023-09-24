package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TextUI {

    GuestService guestService = new GuestService();

    public void readNewGuestData(Scanner input) {
        System.out.println("Tworzymy nowego gościa.");

        try {
            System.out.println("Podaj imię: ");
            String firstName = input.next();
            System.out.println("Podaj nazwisko: ");
            String lastName = input.next();
            System.out.println("Podaj wiek: ");
            int age = input.nextInt();
            System.out.println("Wybierz płeć:");
            System.out.println("\n\t1. Kobieta 2. ężczyzna 3. Nie binarna");
            int genderOption = input.nextInt();
            if (genderOption != 1 && genderOption != 2 && genderOption != 3){
                throw new WrongOptionException("Wrong option in gender selection");
            }
            Guest newGuest = guestService.createNewGuest(firstName, lastName, age, genderOption);
            System.out.println(newGuest.getInfo());
        } catch(InputMismatchException e){
            throw new OnlyNumberException("Use only numbers when chosing gender");
        }
    }
}
