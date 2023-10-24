package org.exampleHotel.domain.reservation.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservationDTO {
    private int id;
    private LocalDateTime from;
    private LocalDateTime to;
    private int roomId;
    private int roomNumber;
    private int guestId;
    private String name;

    public ReservationDTO(int id, LocalDateTime from, LocalDateTime to, int roomId, int roomNumber, int guestId, String name) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.guestId = guestId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getGuestId() {
        return guestId;
    }

    public String getName() {
        return name;
    }
}
