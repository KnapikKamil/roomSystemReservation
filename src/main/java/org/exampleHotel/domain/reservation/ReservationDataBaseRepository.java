package org.exampleHotel.domain.reservation;

import org.exampleHotel.domain.guest.Guest;
import org.exampleHotel.domain.room.Room;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationDataBaseRepository implements ReservationRepository{

    private final static ReservationRepository instance = new ReservationDataBaseRepository();
    private List<Reservation> resrvatons = new ArrayList<>();

    public static ReservationRepository getInstance() {
        return instance;
    }

    @Override
    public Reservation createNewReservation(Room room, Guest guest, LocalDateTime from, LocalDateTime to) {
        return null;
    }

    @Override
    public List<Reservation> getAllReservations() {
        return null;
    }

    @Override
    public void saveAll() {

    }

    @Override
    public List<Reservation> getAll() {
        return this.resrvatons;
    }

    @Override
    public void readAll() {

    }

    @Override
    public void remove(long id) {

    }

    @Override
    public Reservation edit(long id, Room room, Guest guest, LocalDateTime fromWithTime, LocalDateTime toWithTime) {
        return null;
    }
}
