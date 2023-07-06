package org.example.service;

import org.example.entity.Planet;
import org.example.hibernate.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PlanetCrudService {

    private final Session session;

    public PlanetCrudService(Session session) {
        this.session = session;
    }

    public static void main(String[] args) {
        Session session = HibernateUtils.getInstance().getSessionFactory().openSession();

        PlanetCrudService planetService = new PlanetCrudService(session);

        System.out.println("---Insert new planet---");
        System.out.println(planetService.create("PLUTO", "Pluto"));

        System.out.println("---Select planet by id---");
        System.out.println(planetService.getById("JUP"));

        System.out.println("---Update planet name---");
        planetService.update("PLUTO", "PLUTO2006");
        System.out.println(planetService.getById("PLUTO"));

        System.out.println("---Delete planet---");
        planetService.deleteById("PLUTO");
        System.out.println(planetService.getById("PLUTO"));

        System.out.println("---Select all planets---");
        System.out.println(planetService.readAll());

        session.close();
    }

    public Planet create(String id, String name) {
        Transaction transaction = session.beginTransaction();
        Planet newPlanet = new Planet();
        newPlanet.setId(id);
        newPlanet.setName(name);
        session.persist(newPlanet);
        transaction.commit();
        return newPlanet;
    }

    public Planet getById(String id) {
        return session.get(Planet.class, id);
    }


    public void update(String id, String name) {
        Transaction transaction = session.beginTransaction();
        Planet existing = session.get(Planet.class, id);
        existing.setName(name);
        session.persist(existing);
        transaction.commit();
    }

    public void deleteById(String id) {
        Transaction transaction = session.beginTransaction();
        Planet existing = session.get(Planet.class, id);
        session.remove(existing);
        transaction.commit();
    }

    public List<Planet> readAll() {
        return session.createQuery("from Planet", Planet.class).list();
    }
}