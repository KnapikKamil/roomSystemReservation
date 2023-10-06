package org.exampleHotel.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Properties {

    public static final String HOTEL_NAME = "Salt ";
    public final static int SYSTEM_VERSION = 1;
    public final static boolean IS_DEVELOPER_VERSION = true;

    public static final Path DATA_DIRECTORY = Paths.get(System.getProperty("user.home"), "reservation_system");

    public static void createDataDirectory() throws IOException {
        if (!Files.isDirectory(DATA_DIRECTORY)) {
            Files.createDirectory(DATA_DIRECTORY);
        }
    }
}
