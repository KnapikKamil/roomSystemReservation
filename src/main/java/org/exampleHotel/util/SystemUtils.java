package org.exampleHotel.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class SystemUtils {

    private final Properties prop = new Properties();

    public static final String HOTEL_NAME = "Other ";
    public static String SYSTEM_VERSION;
    public final static boolean IS_DEVELOPER_VERSION = true;

    public final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public final static int HOTEL_NIGHT_START_HOUR = 14;
    public final static int HOTEL_NIGHT_START_MINUTE = 30;
    public final static int HOTEL_NIGHT_END_HOUR = 12;
    public final static int HOTEL_NIGHT_END_MINUTE = 45;

    public final static String SINGLE_BED = "Pojedyńcze";
    public final static String DOUBLE_BED = "Podwójne";
    public final static String KING_SIZE = "Królewskie";

    public static final String FEMALE = "Kobieta";
    public static final String MALE = "Mężczyzna";
    public static final String LGBT = "Nie binarna";

    public static final Path DATA_DIRECTORY = Paths.get(System.getProperty("user.home"), "reservation_system");


    public static void createDataDirectory() throws IOException {
        if (!Files.isDirectory(DATA_DIRECTORY)) {
            Files.createDirectory(DATA_DIRECTORY);
        }
    }

    public SystemUtils() {
        try {
            this.prop.load(this.getClass().getClassLoader().getResourceAsStream(".properties"));
            SystemUtils.SYSTEM_VERSION = prop.get("system.version").toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}