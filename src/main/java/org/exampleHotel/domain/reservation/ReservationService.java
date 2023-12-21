package org.exampleHotel.domain.reservation;

import org.exampleHotel.domain.ObjectPool;
import org.exampleHotel.domain.guest.Guest;
import org.exampleHotel.domain.guest.GuestService;
import org.exampleHotel.domain.reservation.dto.ReservationDTO;
import org.exampleHotel.domain.room.Room;
import org.exampleHotel.domain.room.RoomService;
import org.exampleHotel.util.SystemUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationService {

    private final  RoomService roomService = ObjectPool.getRoomService();
    private final GuestService guestService = ObjectPool.getGuestService();
    private final ReservationRepository repository = ObjectPool.getReservationRepository();


    public   ReservationService(){

    }

    public Reservation createNewReservation(LocalDate from, LocalDate to, long roomId, long guestId) throws IllegalArgumentException{
        Room room = this.roomService.getRoomById(roomId);
        Guest guest = this.guestService.getGuestById(guestId);

        LocalDateTime fromWithTime = from.atTime(SystemUtils.HOTEL_NIGHT_START_HOUR, SystemUtils.HOTEL_NIGHT_START_MINUTE);
        LocalDateTime toWithTime = to.atTime(SystemUtils.HOTEL_NIGHT_END_HOUR, SystemUtils.HOTEL_NIGHT_END_MINUTE);
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

    public List<Reservation> getAllReservation() {
        return this.repository.getAll();
    }

    public void remove(long id) {
        this.repository.remove(id);
    }

    public Reservation edit(int id, LocalDate from, LocalDate to, long roomId, long guestId) throws IllegalArgumentException{


        Room room = this.roomService.getRoomById(roomId);
        Guest guest = this.guestService.getGuestById(guestId);

        LocalDateTime fromWithTime = from.atTime(SystemUtils.HOTEL_NIGHT_START_HOUR, SystemUtils.HOTEL_NIGHT_START_MINUTE);
        LocalDateTime toWithTime = to.atTime(SystemUtils.HOTEL_NIGHT_END_HOUR, SystemUtils.HOTEL_NIGHT_END_MINUTE);
        if (toWithTime.isBefore(fromWithTime)){
            throw new IllegalArgumentException();
        }

        return this.repository.edit(id ,room, guest, fromWithTime, toWithTime);
    }
    public List<ReservationDTO> getAsDTO(){
        List<ReservationDTO> result = new ArrayList<>();
        List<Reservation> reservations = repository.getAll();
        for (Reservation reservation : reservations){
            ReservationDTO reservationDTO =  reservation.getAsDTO();
            result.add(reservationDTO);
        }
        return result;
    }
}
