package org.exampleHotel.domain.guest;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

public class GuestJPARepository implements GuestRepository {

    private final static GuestRepository instance = new GuestJPARepository();

    public static GuestRepository getInstance() {
        return instance;
    }

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("thePersistenceUnit");
    private static EntityManager em = factory.createEntityManager();

    @Override
    public Guest createNewGuest(String firstName, String lastName, int age, Gender gender) {
        Guest newGuest = new Guest(firstName, lastName, age, gender);

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(newGuest);
        transaction.commit();

        return newGuest;
    }

    @Override
    public List<Guest> getAll() {
        return em.createQuery("SELECT a FROM Guest a", Guest.class).getResultList();
    }

    @Override
    public void saveAll() {
        System.out.println("Not implemented yet");
    }

    @Override
    public void readAll() {
        System.out.println("Not implemented yet");


    }

    @Override
    public void remove(long id) {
        EntityTransaction tx = em.getTransaction();
        Guest guestToRemove = em.find(Guest.class, id);
        tx.begin();
        em.remove(guestToRemove);
        tx.commit();
        System.out.println("Usunieto: " + guestToRemove.getInfo());
    }

    @Override
    public void edit(long id, String firstName, String lastName, int age, Gender gender) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Guest guest = em.find(Guest.class, id);
        guest.setFirstName(firstName);
        guest.setLastName(lastName);
        guest.setAge(age);
        guest.setGender(gender);
        tx.commit();
        System.out.println("Obiekt gość po edycji to: " + guest.getInfo());
    }

    @Override
    public Guest findById(long id) {
        return em.find(Guest.class, id);
    }
}

