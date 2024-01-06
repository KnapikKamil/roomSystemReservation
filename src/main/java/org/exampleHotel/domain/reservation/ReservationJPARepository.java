package org.exampleHotel.domain.reservation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.exampleHotel.domain.guest.Guest;
import org.exampleHotel.domain.room.Room;

import java.time.LocalDateTime;
import java.util.List;

public class ReservationJPARepository implements ReservationRepository{

    private final static ReservationRepository instance = new ReservationJPARepository();


    private static EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("thePersistenceUnit");
    private static EntityManager em = factory.createEntityManager();

    public static ReservationRepository getInstance() {
        return  instance;
    }
    @Override
    public Reservation createNewReservation(Room room, Guest guest, LocalDateTime from, LocalDateTime to) {
        Reservation newReservation = new Reservation(room, guest, from, to);

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(newReservation);
        tx.commit();

        return newReservation;
    }

    @Override
    public void saveAll() {
        System.out.println("Not implemented yet");
    }

    @Override
    public List<Reservation> getAll() {
        return em.createQuery("SELECT a FROM Reservation a", Reservation.class).getResultList();
    }

    @Override
    public void readAll() {
        System.out.println("Not implemented yet");
    }

    @Override
    public void remove(long id) {

    }

    @Override
    public Reservation edit(long id, Room room, Guest guest, LocalDateTime fromWithTime, LocalDateTime toWithTime) {
        return null;
    }
}
