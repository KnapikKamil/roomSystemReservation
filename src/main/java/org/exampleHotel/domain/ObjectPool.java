package org.exampleHotel.domain;

import org.exampleHotel.domain.guest.GuestJPARepository;
import org.exampleHotel.domain.guest.GuestRepository;
import org.exampleHotel.domain.guest.GuestService;
import org.exampleHotel.domain.reservation.*;
import org.exampleHotel.domain.room.RoomJPARepository;
import org.exampleHotel.domain.room.RoomRepository;
import org.exampleHotel.domain.room.RoomService;

public class ObjectPool {

    private final static RoomService roomService = new RoomService();

    private final static ReservationService reservationService = new ReservationService();



    private ObjectPool(){
    }

    public static GuestService getGuestService() {
        return GuestService.getInstance();
    }

    public static GuestRepository getGuestRepository() {
        return GuestJPARepository.getInstance();
    }


    public static RoomService getRoomService() {
        return roomService;
    }

    public static RoomRepository getRoomRepository() {
        return RoomJPARepository.getInstance();
    }

    public static ReservationService getReservationService() {
        return reservationService;
    }

    public static ReservationRepository getReservationRepository() {
        return ReservationJPARepository.getInstance();
    }
}
