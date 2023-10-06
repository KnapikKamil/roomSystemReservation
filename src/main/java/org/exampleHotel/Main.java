package org.exampleHotel;

import org.exampleHotel.ui.text.TextUI;
import org.exampleHotel.util.Properties;

import java.io.IOException;

public class Main {

    private static final TextUI textUI = new TextUI();

    public static void main(String[] args) {

        try {
            Properties.createDataDirectory();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        textUI.showSystemInfo();
        textUI.showMainMenu();
    }

}