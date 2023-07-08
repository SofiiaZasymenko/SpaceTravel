package org.example.service;

import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.entity.Ticket;
import org.example.hibernate.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TicketCrudService {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final Session session;
    private final ClientCrudService clientService;
    private final PlanetCrudService planetService;

    public TicketCrudService(Session session, ClientCrudService clientService, PlanetCrudService planetService) {
        this.session = session;
        this.clientService = clientService;
        this.planetService = planetService;
    }

    public static void main(String[] args) {
        Session session = HibernateUtils.getInstance().getSessionFactory().openSession();

        ClientCrudService clientService = new ClientCrudService(session);
        PlanetCrudService planetService = new PlanetCrudService(session);
        TicketCrudService ticketService = new TicketCrudService(session, clientService, planetService);

        System.out.println("---Insert new ticket---");
        System.out.println(ticketService.create(clientService.getById(1),
                planetService.getById("JUP"), planetService.getById("MER")));

        System.out.println("---Select ticket by id---");
        System.out.println(ticketService.getById(5));

        System.out.println("---Update ticket name---");
        ticketService.update(12, planetService.getById("JUP"), planetService.getById("EARTH"));
        System.out.println(ticketService.getById(12));

        System.out.println("---Delete ticket---");
        ticketService.deleteById(12);
        System.out.println(ticketService.getById(12));

        System.out.println("---Select all tickets---");
        System.out.println(ticketService.readAll());

        System.out.println("---Pass invalid data---");
        try {
            ticketService.create(null, planetService.getById("JUP"), planetService.getById("MER"));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        try {
            ticketService.update(1, null, planetService.getById("MER"));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        session.close();
    }

    public Ticket create(Client client, Planet planetFrom, Planet planetTo) {
        validateClient(client);
        validatePlanet(planetFrom);
        validatePlanet(planetTo);

        Transaction transaction = session.beginTransaction();
        Ticket newTicket = new Ticket();
        newTicket.setClient(client);
        newTicket.setPlanetFrom(planetFrom);
        newTicket.setPlanetTo(planetTo);
        newTicket.setCreatedAt(LocalDateTime.now());
        session.persist(newTicket);
        transaction.commit();
        return newTicket;
    }

    public Ticket getById(long id) {
        return session.get(Ticket.class, id);
    }

    public void update(long id, Planet planetFrom, Planet planetTo) {
        validatePlanet(planetFrom);
        validatePlanet(planetTo);

        Transaction transaction = session.beginTransaction();
        Ticket existing = session.get(Ticket.class, id);
        if (existing != null) {
            existing.setPlanetFrom(planetFrom);
            existing.setPlanetTo(planetTo);
            session.persist(existing);
            transaction.commit();
        }
    }

    public void deleteById(long id) {
        Ticket existing = session.get(Ticket.class, id);
        if (existing != null) {
            Transaction transaction = session.beginTransaction();
            session.remove(existing);
            transaction.commit();
        }
    }

    public List<Ticket> readAll() {
        return session.createQuery("from Ticket", Ticket.class).list();
    }

    private void validateClient(Client client) {
        if (client == null || clientService.getById(client.getId()) == null) {
            throw new RuntimeException("Client cannot be null!");
        }
    }

    private void validatePlanet(Planet planet) {
        if (planet == null || planetService.getById(planet.getId()) == null) {
            throw new RuntimeException("Planet cannot be null!");
        }
    }
}