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
    public List<Reservation> getAll() {
       return new ArrayList<>(this.reservations);
    }

    @Override
    public void saveAll() {

    }

    @Override
    public void readAll() {
        try {
            Statement statement = SystemUtils.connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM RESERVATIONS");
            while (rs.next()){
                long id = rs.getLong(1);
                long roomId = rs.getLong(2);
                long guestId = rs.getLong(3);
                String fromAsString = rs.getString(4);
                String toASString = rs.getString(5);

                LocalDateTime from = LocalDateTime.parse(fromAsString.replace(" ", "T"), DateTimeFormatter.ISO_DATE_TIME);
                LocalDateTime to = LocalDateTime.parse(toASString.replace(" ", "T"), DateTimeFormatter.ISO_DATE_TIME);


                Reservation newReservation = new Reservation(id, this.roomService.getRoomById(roomId), this.guestService.getGuestById(guestId), from, to);
                this.reservations.add(newReservation);
            }
            statement.close();
        } catch (SQLException throwables) {
            System.out.println("Błąd przy odczycie rezerwacji z bazy danych");
            throw new RuntimeException(throwables);
        }

    }

    @Override
    public void remove(long id) {
        try {
            Statement statement = SystemUtils.connection.createStatement();
            String removeTemplate = "DELETE FROM RESERVATIONS WHERE ID=%d";
            String removeQuery = String.format(removeTemplate, id);
            statement.execute(removeQuery);
            statement.close();

            this.removeById(id);
        } catch (SQLException throwables) {
            System.out.println("Błąd przy usuwaniu rezerwacji");
            throw new RuntimeException(throwables);
        }

    }

    @Override
    public Reservation edit(long id, Room room, Guest guest, LocalDateTime fromWithTime, LocalDateTime toWithTime) {
        try {
            Statement statement = SystemUtils.connection.createStatement();

        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
        return null;
    }

    private void removeById(long id) {
        int indexToBeRemoved = -1;
        for(int i=0; i<this.reservations.size();i++) {
            if(this.reservations.get(i).getId()==id) {
                indexToBeRemoved = i;
                break;
            }
        }
        this.reservations.remove(indexToBeRemoved);
    }
}
