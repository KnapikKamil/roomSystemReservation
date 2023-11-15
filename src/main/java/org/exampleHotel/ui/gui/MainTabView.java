package org.exampleHotel.ui.gui;

import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import org.exampleHotel.ui.gui.guests.GuestsTab;
import org.exampleHotel.ui.gui.reservations.ReservationsTab;
import org.exampleHotel.ui.gui.rooms.RoomsTab;

public class MainTabView {

    private TabPane mainTabs;
    public MainTabView(Stage primaryStage) {
        this.mainTabs = new TabPane();

        RoomsTab roomsTab = new RoomsTab(primaryStage);
        ReservationsTab reservationTab = new ReservationsTab(primaryStage);
        GuestsTab guestsTab = new GuestsTab(primaryStage);

        this.mainTabs.getTabs().addAll(reservationTab.getReservationTab(), guestsTab.getGuestTab(), roomsTab.getRoomTab());
    }
    TabPane getMainTabs() {
        return mainTabs;
    }
}