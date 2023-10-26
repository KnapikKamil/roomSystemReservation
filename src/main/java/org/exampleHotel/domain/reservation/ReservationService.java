package org.exampleHotel.domain.reservation;

import org.exampleHotel.domain.guest.Guest;
import org.exampleHotel.domain.guest.GuestService;
import org.exampleHotel.domain.reservation.dto.ReservationDTO;
import org.exampleHotel.domain.room.Room;
import org.exampleHotel.domain.room.RoomService;
import org.exampleHotel.domain.room.dto.RoomDTO;
import org.exampleHotel.util.Properties;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationService {

    private final  RoomService roomService = RoomService.getInstance();
    private final static GuestService guestService = new GuestService();
    private final  ReservationRepository repository = ReservationRepository.getInstance();
private final static ReservationService instance = new ReservationService();


    private  ReservationService(){

    }

    public Reservation createNewReservation(LocalDate from, LocalDate to, int roomId, int guestId) throws IllegalArgumentException{
        Room room = this.roomService.getRoomById(roomId);
        Guest guest = this.guestService.getGuestById(guestId);

        LocalDateTime fromWithTime = from.atTime(Properties.HOTEL_NIGHT_START_HOUR, Properties.HOTEL_NIGHT_START_MINUTE);
        LocalDateTime toWithTime = to.atTime(Properties.HOTEL_NIGHT_END_HOUR, Properties.HOTEL_NIGHT_END_MINUTE);
        if (toWithTime.isBefore(fromWithTime)){
            throw new IllegalArgumentException();
        }

        return this.repository.createNewReservation(room, guest, fromWithTime, toWithTime);
    }
    public void saveAll() {
        this.repository.saveAll();
    }
    public void readAll(){
        this.repository.readAll();
    }

    public List<Reservation> getAll() {
        return this.repository.getAllReservations();
    }

    public void remove(int id) {
        this.repository.remove(id);
    }

    public Reservation edit(int id, LocalDate from, LocalDate to, int roomId, int guestId) throws IllegalArgumentException{


        Room room = this.roomService.getRoomById(roomId);
        Guest guest = this.guestService.getGuestById(guestId);

        LocalDateTime fromWithTime = from.atTime(Properties.HOTEL_NIGHT_START_HOUR, Properties.HOTEL_NIGHT_START_MINUTE);
        LocalDateTime toWithTime = to.atTime(Properties.HOTEL_NIGHT_END_HOUR, Properties.HOTEL_NIGHT_END_MINUTE);
        if (toWithTime.isBefore(fromWithTime)){
            throw new IllegalArgumentException();
        }

        return this.repository.edit(id ,room, guest, fromWithTime, toWithTime);
    }
    public List<ReservationDTO> getAsDTO(){
        List<ReservationDTO> result = new ArrayList<>();
        List<Reservation> allResetvations = repository.getAll();
        for (Reservation reservation : allResetvations){
            ReservationDTO reservationDTO =  reservation.getAsDTO();
            result.add(reservationDTO);
        }
        return result;
    }
}
