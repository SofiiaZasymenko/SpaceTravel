package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

import static org.example.service.TicketCrudService.DATE_TIME_FORMATTER;

@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
    @ManyToOne
    @JoinColumn(name = "from_planet_id", nullable = false)
    private Planet planetFrom;
    @ManyToOne
    @JoinColumn(name = "to_planet_id", nullable = false)
    private Planet planetTo;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Ticket() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Planet getPlanetFrom() {
        return planetFrom;
    }

    public void setPlanetFrom(Planet planetFrom) {
        this.planetFrom = planetFrom;
    }

    public Planet getPlanetTo() {
        return planetTo;
    }

    public void setPlanetTo(Planet planetTo) {
        this.planetTo = planetTo;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", client=" + client +
                ", planetFrom=" + planetFrom +
                ", planetTo=" + planetTo +
                ", createdAt=" + createdAt.format(DATE_TIME_FORMATTER) +
                '}';
    }
}