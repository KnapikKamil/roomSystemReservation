package org.exampleHotel;

import org.exampleHotel.ui.text.TextUI;

public class Main {

    private static TextUI textUI = new TextUI();

    public static void main(String[] args) {
        String hotelName = "Salt ";
        int systemVersion = 1;
        boolean isDeveloperVersion = true;
        textUI.showSystemInfo(hotelName, systemVersion, isDeveloperVersion);
        textUI.showMainMenu();
    }

}