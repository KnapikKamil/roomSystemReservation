package org.exampleHotel.ui.tui;

import org.exampleHotel.domain.ObjectPool;
import org.exampleHotel.domain.reservation.Reservation;
import org.exampleHotel.domain.reservation.ReservationService;
import org.exampleHotel.exceptions.OnlyNumberException;
import org.exampleHotel.exceptions.PersistenceToFileException;
import org.exampleHotel.exceptions.WrongOptionException;
import org.exampleHotel.domain.guest.Guest;
import org.exampleHotel.domain.guest.GuestService;
import org.exampleHotel.domain.room.Room;
import org.exampleHotel.domain.room.RoomService;
import org.exampleHotel.util.SystemUtils;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TextUI {

    private final GuestService guestService = ObjectPool.getGuestService();
    private final RoomService roomService = ObjectPool.getRoomService();
    private final ReservationService reservationService = ObjectPool.getReservationService();



    private void readNewGuestData(Scanner input) {
        System.out.println("Tworzymy nowego gościa.");

        try {
            System.out.println("Podaj imię: ");
            String firstName = input.next();
            System.out.println("Podaj nazwisko: ");
            String lastName = input.next();
            System.out.println("Podaj wiek: ");
            int age = input.nextInt();
            System.out.println("Wybierz płeć:");
            System.out.println("\n\t1. Kobieta 2. Mężczyzna 3. Nie binarna");
            int genderOption = input.nextInt();
            if (genderOption != 1 && genderOption != 2 && genderOption != 3) {
                throw new WrongOptionException("Wrong option in gender selection");
            }
            Guest newGuest = guestService.createNewGuest(firstName, lastName, age, genderOption);
            System.out.println("Dodano nowego gościa " + newGuest.getInfo());
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Use only numbers when chosing gender");
        }
    }

    private void readNewRoomData(Scanner input) {
        System.out.println("Tworzymy nowy pokój.");
        try {
            System.out.println("Numer: ");
            int number = input.nextInt();
            int[] bedTypes = chooseBedType(input);
            Room newRoom = roomService.createNewRoom(number, bedTypes);
            System.out.println("Dodano nowy pokój: " + newRoom.getInfo());
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Use numbers when creating new room");
        }
    }

    static private int[] chooseBedType(Scanner input) {
        System.out.println("Ile łóżek w pokoju?: ");
        int bedNumber = input.nextInt();
        int[] bedTypes = new int[bedNumber];
        for (int i = 0; i < bedNumber; i = i + 1) {
            System.out.println("Typy łóżek: ");
            System.out.println("\t1. Pojedyncze");
            System.out.println("\t2. Podwójne");
            System.out.println("\t3. Królewskie");
            int bedTypeOption = input.nextInt();
            bedTypes[i] = bedTypeOption;
        }
        return bedTypes;
    }

    public void showSystemInfo() {
        System.out.print("Witam w systemie rezerwacji dla hotelu " + SystemUtils.HOTEL_NAME);
        System.out.println("Aktualna wersja systemu: " + SystemUtils.SYSTEM_VERSION);
        System.out.println("Wersja developerska: " + SystemUtils.IS_DEVELOPER_VERSION);
        System.out.println("\n=========================\n");
    }

    public void showMainMenu() {
        System.out.println("Trwa ładowanie danych..");
       this.guestService.readAll();
        this.roomService.readAll();
        this.reservationService.readAll();

        Scanner input = new Scanner(System.in);
        try {
            performerAction(input);
        } catch (WrongOptionException | OnlyNumberException | PersistenceToFileException e) {
            System.out.println("Wystąpił niespodziewany błąd");
            System.out.println("Kod błędu: " + e.getCode());
            System.out.println("Komunikat błędu: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Wystąpił niespodziewany błąd");
            System.out.println("Nieznany kod błędu");
            System.out.println("Komunikat błędu: " + e.getMessage());
        }
    }


    private void performerAction(Scanner input) {

        int option = -1;
        while (option != 0) {
            option = getActionFromUser(input);

            if (option == 0) {
                System.out.println("Wychodzę z aplikacji, zapisuje dane.");
                this.guestService.saveAll();
                this.roomService.saveAll();
                this.reservationService.saveAll();

            } else if (option == 1) {
                readNewGuestData(input);
            } else if (option == 2) {
                readNewRoomData(input);
            } else if (option == 3) {
                showAllGuests();
            } else if (option == 4) {
                showAllRooms();
            } else if (option == 5) {
                removeGuest(input);
            } else if (option == 6) {
                editGuest(input);
            } else if (option == 7) {
                removeRoom(input);
            } else if (option == 8) {
                editRoom(input);
            } else if (option == 9) {
                createReservation(input);
            } else if (option == 10) {
                showAllReservations(input);
            } else if (option == 11) {
                removeReservation(input);
            } else if (option == 12) {
                editReservation(input);
            } else {
                throw new WrongOptionException("Wrong option in main menu");
            }
        }
    }

    private void editReservation(Scanner input) {
        System.out.println("Podaj id rezerwacji do edycji: ");
        int id = input.nextInt();
        System.out.println("Od kiedy? Format: DD.MM,YYYY:");
        String fromAsString = input.next();
        LocalDate from = LocalDate.parse(fromAsString, SystemUtils.DATE_TIME_FORMATTER);
        System.out.println("Do kiedy? Format: DD.MM.YYYY:");
        String tooASString = input.next();
        LocalDate to = LocalDate.parse(tooASString, SystemUtils.DATE_TIME_FORMATTER);
        System.out.println("Podaj ID pokoju:");
        int roomId = input.nextInt();
        System.out.println("Podaj ID gościa:");
        int guestId = input.nextInt();
        try {
            Reservation res = this.reservationService.edit(id ,from, to, roomId, guestId);
            if (res != null) {
                System.out.println("Zapis rezerwacji się powiódł.");
            }

        }catch (IllegalArgumentException e){
            System.out.println("!!! Data zakończenia rezerwacji nie może być wcześiejsza niż data rozpoczęcia.");
        }

    }

    private void removeReservation(Scanner input) {
        System.out.println("Podaj id rezerwacji do usunięcia: ");
        try {
            int id = input.nextInt();
            this.reservationService.remove(id);
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Use numbers when insert ID");
        }
    }

    private void showAllReservations(Scanner input) {
        List<Reservation> reservations = this.reservationService.getAllReservation();
        for (Reservation reservation : reservations) {
            System.out.println(reservation.getInfo());

        }
    }

    private void createReservation(Scanner input) {
        System.out.println("Od kiedy? Format: DD.MM,YYYY:");
        String fromAsString = input.next();
        LocalDate from = LocalDate.parse(fromAsString, SystemUtils.DATE_TIME_FORMATTER);
        System.out.println("Do kiedy? Format: DD.MM.YYYY:");
        String tooASString = input.next();
        LocalDate to = LocalDate.parse(tooASString, SystemUtils.DATE_TIME_FORMATTER);
        System.out.println("Podaj ID pokoju:");
        int roomId = input.nextInt();
        System.out.println("Podaj ID gościa:");
        int guestId = input.nextInt();
        try {
            Reservation res = this.reservationService.createNewReservation(from, to, roomId, guestId);
            if (res != null) {
                System.out.println("Zapis rezerwacji się powiódł.");
            }

        }catch (IllegalArgumentException e){
            System.out.println("!!! Data zakończenia rezerwacji nie może być wcześiejsza niż data rozpoczęcia.");
        }

    }

    private void editRoom(Scanner input) {
        System.out.println("Podaj id pokoju do edycji: ");
        try {
            int id = input.nextInt();
            System.out.println("Numer: ");
            int number = input.nextInt();
            int[] bedTypes = chooseBedType(input);
            roomService.edit(id, number, bedTypes);
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Use numbers when editing room");
        }
    }

    private void removeRoom(Scanner input) {
        System.out.println("Podaj id pokoju do usunięcia: ");
        try {
            int id = input.nextInt();
            this.roomService.remove(id);
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Use numbers when insert ID");
        }

    }

    private void editGuest(Scanner input) {
        System.out.println("Podaj id gościa do edycji: ");
        try {
            int id = input.nextInt();


            System.out.println("Podaj imię: ");
            String firstName = input.next();
            System.out.println("Podaj nazwisko: ");
            String lastName = input.next();
            System.out.println("Podaj wiek: ");
            int age = input.nextInt();
            System.out.println("Wybierz płeć:");
            System.out.println("\n\t1. Kobieta 2. Mężczyzna 3. Nie binarna");
            int genderOption = input.nextInt();
            if (genderOption != 1 && genderOption != 2 && genderOption != 3) {
                throw new WrongOptionException("Wrong option in gender selection");
            }
            guestService.edit(id, firstName, lastName, age, genderOption);
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Use numbers when edting guest");
        }
    }


    private void removeGuest(Scanner input) {
        System.out.println("Podaj id gościa do usunięcia: ");
        try {
            int id = input.nextInt();
            this.guestService.remove(id);
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Use numbers when insert ID");
        }
    }

    private void showAllRooms() {
        List<Room> rooms = this.roomService.getAllRooms();
        for (Room room : rooms) {
            System.out.println(room.getInfo());
        }
    }

    private void showAllGuests() {
        List<Guest> guests = this.guestService.getAllGuests();
        for (Guest guest : guests) {
            System.out.println(guest.getInfo());
        }
    }


    private int getActionFromUser(Scanner in) {
        System.out.println("0 - Wyjście z aplikacji(zapis danych)");
        System.out.println("1 - Dodaj nowego gościa.");
        System.out.println("2 - Dodaj nowy pokój.");
        System.out.println("3 - Wypisz wszystkich gości.");
        System.out.println("4 - Wypisz wszystkie pokoje. ");
        System.out.println("5 - Usuń gościa.");
        System.out.println("6 - Edytuj dane gościa.");
        System.out.println("7 - Usuń pokój.");
        System.out.println("8 - Edytuj pokój. ");
        System.out.println("9 - stwórz rezerwację.");
        System.out.println("10 - Wypisz wszystkie rezerwacje.");
        System.out.println("11 - Usuń rezerwację.");
        System.out.println("12 - Edytuj rezerwację.");
        System.out.println("Wybierz opcję: ");
        int option;
        try {
            option = in.nextInt();
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Wrong use only numbers in main menu");
        }
        return option;
    }
}

