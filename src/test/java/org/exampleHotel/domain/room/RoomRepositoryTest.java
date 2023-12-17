package org.exampleHotel.domain.room;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoomRepositoryTest {

    @Test
    public void getAllRoms(){

        RoomDatabaseRepository repository = new RoomDatabaseRepository();
        repository.setConnector(new MockDatabaseRoomConnector());

        repository.readAll();
        List<Room> allRooms = repository.getAll();

        assertEquals(2, allRooms.size());
        assertEquals(2, allRooms.get(0).getBeds().size());
        assertEquals(1, allRooms.get(1).getBeds().size());

    }

    @Test
    public void removeByIDTest() {
        RoomDatabaseRepository repository = new RoomDatabaseRepository();
        repository.setConnector(new MockDatabaseRoomConnector());

        repository.readAll();
        List<Room> allRooms = repository.getAll();
        repository.remove(1);

        assertEquals(1, allRooms.size());
        assertEquals(2, allRooms.get(0).getId());

    }
}
