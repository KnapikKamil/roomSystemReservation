package org.exampleHotel;

import javafx.application.Application;

import javafx.stage.Stage;
import org.exampleHotel.domain.guest.GuestService;
import org.exampleHotel.domain.reservation.ReservationService;
import org.exampleHotel.domain.room.RoomService;
import org.exampleHotel.exceptions.PersistenceToFileException;
import org.exampleHotel.ui.gui.PrimaryStage;
import org.exampleHotel.ui.tui.TextUI;
import org.exampleHotel.util.Properties;

import java.io.IOException;

public class Main extends Application {

    private static final GuestService guestService = new GuestService();
    private static final RoomService roomService = new RoomService();
    private static final ReservationService reservationService = new ReservationService();

    private static final TextUI textUI = new TextUI();


    public static void main(String[] args) {

        try {
            Properties.createDataDirectory();
            System.out.println("Trwa ładowanie danych..");
            guestService.readAll();
            roomService.readAll();
            reservationService.readAll();
        } catch (IOException e) {
            throw new PersistenceToFileException(Properties.DATA_DIRECTORY.toString(), "create", "directory");
        }

        Application.launch(args);

      //   textUI.showSystemInfo();
      //   textUI.showMainMenu();
    }
    public  void start(Stage primaryStage) {
        PrimaryStage primary = new PrimaryStage();
        primary.initialize(primaryStage);


         }



    }

