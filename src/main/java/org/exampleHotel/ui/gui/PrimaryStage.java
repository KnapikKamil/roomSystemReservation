package org.exampleHotel.ui.gui;

import javafx.scene.Scene;

import javafx.stage.Stage;
import org.exampleHotel.util.Properties;

public class PrimaryStage {

    public void initialize(Stage primaryStage){
        String hotelName = Properties.HOTEL_NAME;
        int systemVersion = Properties.SYSTEM_VERSION;

MainTabView mainTabView = new MainTabView(primaryStage);

        Scene scene = new Scene(mainTabView.getMainTabs(), 1280,640);
        String title = String.format("System rezerwacji hotelu %s (%d)", hotelName, systemVersion);
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
