package org.exampleHotel.domain.room;

import org.exampleHotel.domain.ObjectPool;
import org.exampleHotel.domain.reservation.ReservationService;
import org.exampleHotel.util.SystemUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RoomServiceTest {

    @Test
    public void convertFromIntOptionsIntoBedTypesTest(){

        RoomService roomService = ObjectPool.getRoomService();

        List<String> bedTypeOptions = new ArrayList<>();
        bedTypeOptions.add(SystemUtils.SINGLE_BED);
        bedTypeOptions.add(SystemUtils.DOUBLE_BED);
        bedTypeOptions.add(SystemUtils.KING_SIZE);

        roomService.getBedTypes(bedTypeOptions);

        assertEquals(3, bedTypeOptions.size());
        assertEquals("Pojedyńcze", SystemUtils.SINGLE_BED);
        assertEquals("Podwójne", SystemUtils.DOUBLE_BED);
        assertEquals("Królewskie", SystemUtils.KING_SIZE);

    }

    @Test
    public void getAvailableRoomsShouldThrowExceptionWhenNullParam(){

        RoomService roomService = new RoomService();

        assertThrows(IllegalArgumentException.class, () -> roomService.getAvailableRooms(null, null));
    }

    @Test
    public void getAvailableRoomsShouldThrowExceptionWhenToDateIsBeforeFrom(){

        RoomService roomService = new RoomService();
        LocalDate from = LocalDate.parse("2023-12-23");
        LocalDate to = LocalDate.parse("2023-11-11");

        assertThrows(IllegalArgumentException.class, () -> {
            roomService.getAvailableRooms(from, to);
        });
    }
    @Test
    public void getAvailableRoomsShouldReturnAllRoomsWhenNoReservations(){

        RoomRepository roomRepository = mock(RoomRepository.class);
        when(roomRepository.getAll()).thenReturn(new ArrayList<>());
        RoomService roomService = new RoomService();
        roomService.setRepository(roomRepository);

        LocalDate from = LocalDate.parse("2023-11-23");
        LocalDate to = LocalDate.parse("2023-12-11");

        List<Room> availableRooms = roomService.getAvailableRooms(from, to);

        assertEquals(0, availableRooms.size());
    }

    @Test
    public void  getAvailableRoomsShouldReturnEmptyListWhenNoRoomsInSystem(){

        RoomRepository roomRepository = mock(RoomRepository.class);
        Room room = new Room(1, 666, null);
        List<Room> rooms = new ArrayList<>();
        rooms.add(room);
        when(roomRepository.getAll()).thenReturn(rooms);

        ReservationService reservationService = mock(ReservationService.class);
        when(reservationService.getAllReservation()).thenReturn(new ArrayList<>());

        RoomService roomService = new RoomService();
        roomService.setRepository(roomRepository);
        roomService.setResrvationService(reservationService);

        LocalDate from = LocalDate.parse("2023-11-23");
        LocalDate to = LocalDate.parse("2023-12-11");

        List<Room> availableRooms = roomService.getAvailableRooms(from, to);

        assertEquals(1, availableRooms.size());

    }

}
