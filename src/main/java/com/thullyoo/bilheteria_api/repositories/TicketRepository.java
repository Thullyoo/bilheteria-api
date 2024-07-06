package com.thullyoo.bilheteria_api.repositories;

import com.thullyoo.bilheteria_api.entities.ticket.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Optional<Ticket> findByChair(String chair);
}
