package org.exampleHotel.ui.gui;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;

public class RoomsTab {
    private Tab troomTab;

    public RoomsTab() {
        this.troomTab = new Tab("Pokoje", new Label("Obsługa pokoi"));
        this.troomTab.setClosable(false);
    }

    public Tab getTroomTab() {
        return troomTab;
    }
}
