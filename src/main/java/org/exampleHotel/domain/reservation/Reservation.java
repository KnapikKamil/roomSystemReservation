package org.exampleHotel.domain.reservation;

import org.exampleHotel.domain.guest.Guest;
import org.exampleHotel.domain.reservation.dto.ReservationDTO;
import org.exampleHotel.domain.room.Room;

import java.time.LocalDateTime;

public class Reservation {

    private final int id;
    private final Room room;
    private final Guest guest;
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Reservation(int id, Room room, Guest guest, LocalDateTime from, LocalDateTime to) {
        this.id = id;
        this.room = room;
        this.guest = guest;
        this.from = from;
        this.to = to;
    }

    String toCSV() {
        return String.format("%s,%s,%s,%s,%s%s", this.id, getRoomId(), getGuestId(), this.from.toString(), this.to.toString(), System.getProperty("line.separator"));
    }

    public String getInfo() {
        return String.format("%s,%s,%s,%s,%s", this.id, getRoomId(), getGuestId(), this.from.toString(), this.to.toString());
    }

    int getId() {
        return this.id;
    }

    public ReservationDTO getAsDTO() {
        return new ReservationDTO(this.id, this.from, this.to,getRoomId(), getRoomNumber(), getGuestId(), getGuestName());
    }

    private int getRoomId() {
        if (this.room != null) {
            return (int) this.room.getId();
        } else {
            return 0;
        }
    }

    private int getRoomNumber() {
        if (this.room != null) {
            return this.room.getNumber();
        } else {
            return 0;
        }
    }

    private int getGuestId() {
        if (this.guest != null) {
            return (int)this.guest.getId();
        } else {
            return 0;
        }
    }

    private String getGuestName() {
        if (this.guest != null) {
            return this.guest.getFirstName() + " " + this.guest.getLastName();
        } else {
            return "";
        }
    }

}
