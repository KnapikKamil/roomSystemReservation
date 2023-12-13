package org.exampleHotel.domain.guest;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GuestTest {

    @Test
    public void getasDTOTest() {

        Guest guest = new Guest(1, "Grzegorz", "Braun", 56, Gender.MALE);
        Guest guest2 = new Guest(2, "Bożena", "Nowak", 33, Gender.FEMALE);
        Guest guest3 = new Guest(3, "Ktoś", "Coś", 99, Gender.LGBTQ);


        String toCSVTemplate = "1,Grzegorz,Braun,56,Mężczyzna" + System.getProperty("line.separator");
        String toCSVTemplate2 = "2,Bożena,Nowak,33,Kobieta" + System.getProperty("line.separator");
        String toCSVTemplate3 = "3,Ktoś,Coś,99,Nie binarna" + System.getProperty("line.separator");

        String createdCSV = guest.toCSV();
        String createdCSV2 = guest2.toCSV();
        String createdCSV3 = guest3.toCSV();


        assertEquals(toCSVTemplate, createdCSV, "Porównanie wygenerowanych formatów CSV guest");
        assertEquals(toCSVTemplate2, createdCSV2, "Porównanie wygenerowanych formatów CSV guest2");
        assertEquals(toCSVTemplate3, createdCSV3, "Porównanie wygenerowanych formatów CSV guest3");

    }

    @Test
    public void toCSVWithNullGenderTest() {
        Guest guest = new Guest(1, "Jarosław", "Kaczyński", 74, null);

        if (guest.getGender() == null) {
            System.out.println("Płeć nie powinna być null");
        }

        String toCSVTemplate = "1,Jarosław,Kaczyński,74,null" + System.getProperty("line.separator");
        String createdCSV = guest.toCSV();

        assertEquals(toCSVTemplate, createdCSV, "Porównanie wygenerowanych formatów CSV przy gender == null");
    }
}

