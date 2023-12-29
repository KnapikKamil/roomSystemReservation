package org.exampleHotel;

import javafx.application.Application;

import javafx.stage.Stage;
import org.exampleHotel.domain.ObjectPool;
import org.exampleHotel.domain.guest.Gender;
import org.exampleHotel.domain.guest.Guest;
import org.exampleHotel.domain.guest.GuestJPARepository;
import org.exampleHotel.domain.guest.GuestService;
import org.exampleHotel.domain.reservation.ReservationService;
import org.exampleHotel.domain.room.RoomService;
import org.exampleHotel.exceptions.PersistenceToFileException;
import org.exampleHotel.ui.gui.PrimaryStage;
import org.exampleHotel.util.SystemUtils;

import java.io.IOException;
import java.util.List;

public class Main extends Application {


    private static final GuestService guestService = ObjectPool.getGuestService();
    private static final RoomService roomService = ObjectPool.getRoomService();
    private static final ReservationService reservationService = ObjectPool.getReservationService();

   //  private static final TextUI textUI = new TextUI();

    public static void main(String[] args) {

        try {
            SystemUtils su = new SystemUtils();
            SystemUtils.createDataDirectory();
            su.createDataBaseConnection();
            System.out.println("Trwa ładowanie danych..");
          //  testowanie JPA
          //  GuestJPARepository guestJPARepository = new GuestJPARepository();
          //  Guest newGuest = guestJPARepository.createNewGuest("Bonfacy", "Bąkiewicz", 99, Gender.MALE);
          //  List<Guest> allGuests = guestJPARepository.getAll();
          //  for (Guest guest : allGuests){
          //      System.out.println(guest.getInfo());
          //  }
//
          //  guestJPARepository.remove(newGuest.getId());
//
          //  guestJPARepository.edit(newGuest.getId(), "Mason", "Moore", 42, Gender.FEMALE);

            guestService.readAll();
            roomService.readAll();
            reservationService.readAll();
        } catch (IOException e) {
            throw new PersistenceToFileException(SystemUtils.DATA_DIRECTORY.toString(), "create", "directory");
        }

        Application.launch(args);

        //   textUI.showSystemInfo();
        //   textUI.showMainMenu();
    }

    @Override
    public void start(Stage primaryStage) {
        PrimaryStage primary = new PrimaryStage();
        primary.initialize(primaryStage);


    }

    @Override
    public void stop() {
        System.out.println(SystemUtils.MALE+" Wychodzę z aplikacji, zapisuje dane.");
        guestService.saveAll();
        roomService.saveAll();
        reservationService.saveAll();
    }


}

