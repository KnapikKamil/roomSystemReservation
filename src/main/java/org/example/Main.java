package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String hotelName = "Salt Bochnia";
        int systemVersion = 1;
        boolean isDeveloperVersion = true;
        System.out.print("Witam w systemie rezerwacji dla hotelu");
        System.out.println(hotelName);
        System.out.println("Aktualna wersja systemu: ");
        System.out.println(systemVersion);
        System.out.println("Wersja developerska: ");
        System.out.println(isDeveloperVersion);
        System.out.println("\n=========================\n");
        Scanner input = new Scanner(System.in);
        System.out.println("1. Dodaj nowego gościa.");
        System.out.println("2. Dodaj nowy pokój.");
        System.out.println("3. Wyszukaj gościa.");
        System.out.println("Wybierz opcję: ");
        int option = 0;
        try {
            option = input.nextInt();
        } catch (Exception e) {
            System.out.println("Niepoprawne dane wejsciowe, wprowadz liczbę.");
            e.printStackTrace();
        }
        if (option == 1) {
            System.out.println("Tworzymy gościa: ");
            try {
                System.out.println("Podaj imię: ");
                String firstName = input.next();
                System.out.println("Podaj nazwisko: ");
                String lastName = input.next();
                System.out.println("Podaj wiek: ");
                int age = input.nextInt();
                Guest createdGuest = new Guest(firstName, lastName, age);
                String info = String.format("Dodano nowego gościa: %s %s (%d)", createdGuest.firstName, createdGuest.lastName, createdGuest.age);
                System.out.println(info);
                } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Podaj wiek przez cyfry");
            }
        } else if (option == 2) {
            System.out.println("Tworzymy pokój: ");
            try {
                System.out.println("Podaj numer pokoju: ");
                int roomNr = input.nextInt();
                System.out.println("Podaj ilość łóżek: ");
                int beds = input.nextInt();
                Room createdRoom = new Room(roomNr, beds);
                String info = String.format("Dodano pokój o numerze: %d, łóżek: %d", createdRoom.roomNr, createdRoom.beds);
                System.out.println(info);
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("Używaj cyfr!");
            }
        } else if (option == 3) {
            System.out.println("Wybrano opcję 3.");
        } else {
            System.out.println("Wybrano niepoprawną akcję.");
        }

    }
}

