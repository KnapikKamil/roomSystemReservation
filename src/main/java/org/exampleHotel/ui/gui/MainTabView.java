package org.exampleHotel.ui.gui;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MainTabView {

    private TabPane mainTabs;
    public MainTabView() {
        this.mainTabs = new TabPane();
        Tab reservationTab = new Tab("Rezerwacje", new Label("Obsługa rezerwacji"));
        Tab guestTab = new Tab("Goście", new Label("Obsługa gości"));

        reservationTab.setClosable(false);
        guestTab.setClosable(false);

        RoomsTab roomsTab = new RoomsTab();

        this.mainTabs.getTabs().addAll(reservationTab, guestTab, roomsTab.getTroomTab());
    }
    TabPane getMainTabs() {
        return mainTabs;
    }
}