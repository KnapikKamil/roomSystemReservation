package org.exampleHotel.domain.reservation;


import org.exampleHotel.domain.guest.Gender;
import org.exampleHotel.domain.guest.Guest;
import org.exampleHotel.domain.reservation.dto.ReservationDTO;
import org.exampleHotel.domain.room.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResrvationTest {
    Room room;
    private Guest guest;
    private LocalDateTime from;
    private LocalDateTime to;

    @BeforeEach
    void setUp() {
        room = new Room(1, 101, null);
        guest = new Guest(1, "John", "Doe", 30, Gender.MALE);
        from = LocalDateTime.now();
        to = from.plusDays(3);
    }

    @Test
    void testGetRoomNumber() {
        Reservation reservation = new Reservation(1, room, guest, from, to);
        assertEquals(room.getNumber(), reservation.getRoomNumber());
    }

    @Test
    void testGetGuestId() {
        Reservation reservation = new Reservation(1, room, guest, from, to);
        assertEquals(guest.getId(), reservation.getGuestId());
    }

    @Test
    void testGetGuestName() {
        Reservation reservation = new Reservation(1, room, guest, from, to);
        assertEquals(guest.getFirstName() + " " + guest.getLastName(), reservation.getGuestName());
    }

    @Test
    void testGetAsDTO() {
        Reservation reservation = new Reservation(1, room, guest, from, to);
        ReservationDTO dto = reservation.getAsDTO();
        assertEquals(reservation.getId(), dto.getId());
        assertEquals(reservation.getFrom(), dto.getFrom());
        assertEquals(reservation.getTo(), dto.getTo());
        assertEquals(reservation.getRoomId(), dto.getRoomId());
        assertEquals(reservation.getRoomNumber(), dto.getRoomNumber());
        assertEquals(reservation.getGuestId(), dto.getGuestId());
        assertEquals(reservation.getGuestName(), dto.getName());
    }
}
