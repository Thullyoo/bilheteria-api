package com.thullyoo.bilheteria_api.services;

import com.thullyoo.bilheteria_api.entities.session.Session;
import com.thullyoo.bilheteria_api.entities.ticket.Ticket;
import com.thullyoo.bilheteria_api.entities.ticket.TicketRequest;
import com.thullyoo.bilheteria_api.entities.ticket.TicketResponse;
import com.thullyoo.bilheteria_api.repositories.SessionRepository;
import com.thullyoo.bilheteria_api.repositories.TicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    private final SessionRepository sessionRepository;

    public TicketService(TicketRepository ticketRepository, SessionRepository sessionRepository) {
        this.ticketRepository = ticketRepository;
        this.sessionRepository = sessionRepository;
    }

    @Transactional
    public TicketResponse registerTicket(Long session_id, TicketRequest ticketRequest){
        Optional<Session> session = sessionRepository.findById(session_id);
        if (session.isEmpty()){
            throw new RuntimeException("Sessão não encontrada.");
        }
        if (ticketRepository.findByChair(ticketRequest.chair()).isPresent()){
            throw new RuntimeException("Cadeira " + ticketRequest.chair() + " já está reservada.");
        }

        Ticket ticket = new Ticket();
        ticket.setValue(ticketRequest.value());
        ticket.setChair(ticketRequest.chair());
        ticket.setSession(session.get());

        ticket = ticketRepository.save(ticket);

        Set<Ticket> ticketSet =  session.get().getTickets();

        ticketSet.add(ticket);

        session.get().setTickets(ticketSet);

        return new TicketResponse(ticket.getId(), session.get().getId(), ticket.getChair(), ticket.getValue());
    }

}
