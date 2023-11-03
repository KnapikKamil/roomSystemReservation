package org.exampleHotel.domain;

import org.exampleHotel.domain.guest.GuestRepository;
import org.exampleHotel.domain.guest.GuestService;
import org.exampleHotel.domain.reservation.ReservationRepository;
import org.exampleHotel.domain.reservation.ReservationService;
import org.exampleHotel.domain.room.RoomRepository;
import org.exampleHotel.domain.room.RoomService;

public class ObjectPool {

    private ObjectPool(){
    }

    public static GuestService getGuestService() {
        return GuestService.getInstance();
    }

    public static GuestRepository getGuestRepository() {
        return GuestRepository.getInstance();
    }

    public static RoomService getRoomService() {
        return RoomService.getInstance();
    }

    public static RoomRepository getRoomRepository() {
        return RoomRepository.getInstance();
    }

    public static ReservationService getReservationService() {
        return ReservationService.getInstance();
    }

    public static ReservationRepository getReservationRepository() {
        return ReservationRepository.getInstance();
    }
}
