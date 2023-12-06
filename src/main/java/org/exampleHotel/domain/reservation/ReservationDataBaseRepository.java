package org.exampleHotel.domain.reservation;

import org.exampleHotel.domain.ObjectPool;
import org.exampleHotel.domain.guest.Guest;
import org.exampleHotel.domain.guest.GuestService;
import org.exampleHotel.domain.room.Room;
import org.exampleHotel.domain.room.RoomService;
import org.exampleHotel.util.SystemUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReservationDataBaseRepository implements ReservationRepository {
    private final static ReservationRepository instance = new ReservationDataBaseRepository();
    private final RoomService roomService = ObjectPool.getRoomService();
    private final GuestService guestService = ObjectPool.getGuestService();
    private List<Reservation> reservations = new ArrayList<>();
    public static ReservationRepository getInstance() {
        return instance;
    }
    @Override
    public Reservation createNewReservation(Room room, Guest guest, LocalDateTime from, LocalDateTime to) {
        try {
            String fromAsStr = from.format(DateTimeFormatter.ISO_DATE_TIME);
            String toAsStr = to.format(DateTimeFormatter.ISO_DATE_TIME);
            Statement statement = SystemUtils.connection.createStatement();
            String createTemplate = "INSERT INTO RESERVATIONS(ROOM_ID, GUEST_ID, RES_FROM, RES_TO) VALUES (%d, %d, '%s', '%s')";
            String createQuery = String.format(createTemplate, room.getId(), guest.getId(), fromAsStr, toAsStr);
            statement.execute(createQuery, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.getGeneratedKeys();
            long id = -1;
            while (rs.next()) {
                id = rs.getLong(1);
            }
            Reservation newReservation = new Reservation(id, room, guest, from, to);
            this.reservations.add(newReservation);
            return newReservation;
        } catch (SQLException throwables) {
            System.out.println("Błąd przy tworzeniu rezerwacji");
            return null;
        }
    }



    @Override
    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        try {
            Statement statement = SystemUtils.connection.createStatement();
            String query = "SELECT * FROM RESERVATIONS";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                long id = rs.getLong("ID");
                long roomId = rs.getLong("ROOM_ID");
                Room room = roomService.getRoomById(roomId);
                long guestId = rs.getLong("GUEST_ID");
                Guest guest = guestService.getGuestById(guestId);
                LocalDateTime from = LocalDateTime.parse(rs.getString("RES_FROM"));
                LocalDateTime to = LocalDateTime.parse(rs.getString("RES_TO"));
                Reservation reservation = new Reservation(id, room, guest, from, to);
                reservations.add(reservation);
            }
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
        return reservations;
    }

    @Override
    public void saveAll() {

    }

    @Override
    public List<Reservation> getAll() {
        return this.reservations;
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
