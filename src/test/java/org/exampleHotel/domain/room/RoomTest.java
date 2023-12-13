package org.exampleHotel.domain.room;

import org.exampleHotel.domain.room.dto.RoomDTO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {

    /* Struktura testów jednostkowych
    given - dane wejściowe

    when - jak operacja jest wykonana

    then - co ma się wydarzyć/ czego się spodziewamy


     */

    @Test
    public void toCSVTest(){
        List<BedType>beds = Arrays.asList(BedType.values());

        Room room = new Room(1, 999, beds);

        String toCSVTemplate = "1,999,Pojedyńcze#Podwójne#Królewskie" + System.getProperty("line.separator");
        String createdCSV = room.toCSV();

        assertEquals(toCSVTemplate, createdCSV, "Porównanie wygenerowanych formatów CSV");


    }

    @Test
    public void toCSVWithNullBedListTest(){
        Room room = new Room(1, 999, null);

        assertNotNull(room.getBeds());

        String toCSVTemplate = "1,999," + System.getProperty("line.separator");
        String createdCSV = room.toCSV();

        assertEquals(toCSVTemplate, createdCSV, "Porównanie wygenerowanych formatów CSV przy liście łóżek == null");
    }

    @Test
    public void toDTOTest(){
        List<BedType>beds = Arrays.asList(BedType.values());

        Room room = new Room(1, 999, beds);

        RoomDTO roomDTO = room.generateDTO();

        assertEquals(roomDTO.getId(), 1);
        assertEquals(roomDTO.getNumber(), 999);
        assertEquals(roomDTO.getBedsCount(), 3);
        assertEquals(roomDTO.getRoomSize(), 5);
        assertEquals(roomDTO.getBeds(), "Pojedyńcze,Podwójne,Królewskie");
    }

    @Test
    public void toDTOFromRoomWithNullBedsListTes(){

        Room room = new Room(1, 999, null);

        RoomDTO roomDTO = room.generateDTO();

        assertEquals(roomDTO.getId(), 1);
        assertEquals(roomDTO.getNumber(), 999);
        assertEquals(roomDTO.getBedsCount(), 0);
        assertEquals(roomDTO.getRoomSize(), 0);
        assertEquals(roomDTO.getBeds(), "");

    }

}
