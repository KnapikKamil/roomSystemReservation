package org.exampleHotel.domain.reservation;

import org.exampleHotel.domain.guest.Guest;
import org.exampleHotel.domain.room.Room;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository {
    Reservation createNewReservation(Room room, Guest guest, LocalDateTime from, LocalDateTime to);



    List<Reservation> getAllReservations();

    void saveAll();

    List<Reservation> getAll();

    void readAll();

    void remove(long id);

    Reservation edit(long id, Room room, Guest guest, LocalDateTime fromWithTime, LocalDateTime toWithTime);
}
