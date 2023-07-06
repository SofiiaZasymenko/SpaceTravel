package org.example.service;

import org.example.entity.Client;
import org.example.hibernate.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClientCrudService {

    private final Session session;

    public ClientCrudService(Session session) {
        this.session = session;
    }

    public static void main(String[] args) {
        Session session = HibernateUtils.getInstance().getSessionFactory().openSession();

        ClientCrudService clientService = new ClientCrudService(session);

        System.out.println("---Insert new client---");
        System.out.println(clientService.create("George"));
        System.out.println(clientService.create("Megan"));
        System.out.println(clientService.create("William"));

        System.out.println("---Select client by id---");
        System.out.println(clientService.getById(8));

        System.out.println("---Update client name---");
        clientService.update(13, "Kate");
        System.out.println(clientService.getById(7));

        System.out.println("---Delete client---");
        clientService.deleteById(12);
        System.out.println(clientService.getById(12));

        System.out.println("---Select all clients---");
        System.out.println(clientService.readAll());

        session.close();
    }

    public Client create(String name) {
        Transaction transaction = session.beginTransaction();
        Client newClient = new Client();
        newClient.setName(name);
        session.persist(newClient);
        transaction.commit();
        return newClient;
    }

    public Client getById(long id) {
        return session.get(Client.class, id);
    }

    public void update(long id, String name) {
        Transaction transaction = session.beginTransaction();
        Client existing = session.get(Client.class, id);
        existing.setName(name);
        session.persist(existing);
        transaction.commit();
    }

    public void deleteById(long id) {
        Client existing = session.get(Client.class, id);
        if (existing != null) {
            Transaction transaction = session.beginTransaction();
            session.remove(existing);
            transaction.commit();
        }
    }

    public List<Client> readAll() {
        return session.createQuery("from Client", Client.class).list();
    }
}