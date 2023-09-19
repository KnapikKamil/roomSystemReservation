package org.example;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String hotelName = "Salt ";
        int systemVersion = 1;
        boolean isDeveloperVersion = true;
        showSystemInfo(hotelName, systemVersion, isDeveloperVersion);
        Scanner input = new Scanner(System.in);
        try {
            performerAction(input);
        }catch(WrongOptionExeption e){
            System.out.println("Wystąpił niespodziewany błąd");
            System.out.println("Kod błędu: " + e.getCode());
            System.out.println("Komunikat błędu: " + e.getMessage());
        }catch (OnlyNumberExeption e){
            System.out.println("Wystąpił niespodziewany błąd");
            System.out.println("Kod błędu: " + e.getCode());
            System.out.println("Komunikat błędu: " + e.getMessage());
        }catch (Exception e) {
            System.out.println("Wystąpił niespodziewany błąd");
            System.out.println("Nieznany kod błędu");
            System.out.println("Komunikat błędu: " + e.getMessage());
        }

    }

    private static void performerAction(Scanner input) {
        int option = getActionFromUser(input);
        if (option == 1) {
            Guest newGuest = createNewGuest(input);
        } else if (option == 2) {
            Room newRoom = createNewRoom(input);
        } else if (option == 3) {
            System.out.println("Wybrano opcję 3.");
        } else {
            throw new WrongOptionExeption("Wrong option in main menu");
        }
    }

    private static void showSystemInfo(String hotelName, int systemVersion, boolean isDeveloperVersion) {
        System.out.print("Witam w systemie rezerwacji dla hotelu " + hotelName);
        System.out.println("Aktualna wersja systemu: " + systemVersion);
        System.out.println("Wersja developerska: " + isDeveloperVersion);
        System.out.println("\n=========================\n");
    }
    private static int getActionFromUser(Scanner in) {
        System.out.println("1. Dodaj nowego gościa.");
        System.out.println("2. Dodaj nowy pokój.");
        System.out.println("3. Wyszukaj gościa.");
        System.out.println("Wybierz opcję: ");
        int option = 0;
        try {
            option = in.nextInt();
        } catch (InputMismatchException e) {
            throw new OnlyNumberExeption("Wrong use only numbers in main menu");
        }
        return option;
    }
     private static Guest createNewGuest(Scanner input) {
        System.out.println("Tworzymy nowego gościa.");
        Gender genderType = genderSelection(input);
        try {
            System.out.println("Podaj imię: ");
            String firstName = input.next();
            System.out.println("Podaj nazwisko: ");
            String lastName = input.next();
            System.out.println("Podaj wiek: ");
            int age = input.nextInt();
            Guest newGuest = new Guest(genderType, firstName, lastName, age);
            System.out.println(newGuest.getInfo());
            return newGuest;
        } catch (InputMismatchException e) {
            throw new OnlyNumberExeption("Use only numbers when chosing gender");
        }
    }
    private static Room createNewRoom(Scanner input) {
        System.out.println("Tworzymy nowy pokój.");
        try {
            System.out.println("Numer: ");
            int number = input.nextInt();
            BedType[] bedTypes = chooseBedType(input);
            Room newRoom = new Room(number, bedTypes);
            System.out.println(newRoom.getInfo());
            return newRoom;
        } catch (InputMismatchException e) {
            throw new OnlyNumberExeption("se numbers when creating new room");
        }
    }

    private static BedType[] chooseBedType(Scanner input) {
        System.out.println("Ilość łóżek w pokoju?");
        int bedNumber = input.nextInt();
        BedType[] bedTypes = new BedType[bedNumber];
        for(int i=0;i<bedNumber;i++) {
            System.out.println("Typy łóżek: ");
            System.out.println("\t1. Pojedyncze");
            System.out.println("\t2. Podwójne");
            System.out.println("\t3. Królewskie");
            BedType bedType = BedType.SINGLE;
            int bedTypeOption = input.nextInt();
            if (bedTypeOption == 1) {
                bedType = BedType.SINGLE;
            } else if (bedTypeOption == 2) {
                bedType = BedType.DOUBLE;
            } else if (bedTypeOption == 3) {
                bedType = BedType.KING_SIZE;
            }else {
                throw new WrongOptionExeption("Wrong option when selecting bed type");
            }
            bedTypes[i] = bedType;
        }
        return bedTypes;
    }
    private static Gender genderSelection(Scanner input){
        System.out.println("Wybierz płeć:");
        System.out.println("\n\t1. Kobieta");
        System.out.println("\t2. ężczyzna");
        System.out.println("\t3. Nie binarna");
        Gender genderType = Gender.LGBTQ;
        int genderTypeOption = input.nextInt();
        if (genderTypeOption == 1){
            genderType = Gender.FAMELE;
        } else if (genderTypeOption == 2) {
            genderType = Gender.MALE;
        } else if (genderTypeOption == 3) {
            genderType = Gender.LGBTQ;
        }else{
            throw new WrongOptionExeption("Wrong option in gender selection");
        }
        return genderType;
    }
}