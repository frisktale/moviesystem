package com.frisk.service.test;

import com.frisk.MovieException;
import com.frisk.entity.Ticket;
import com.frisk.service.TicketService;
import com.frisk.service.impl.TicketServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * User: frisk
 * Date: 2018/7/30
 * Time: 9:55
 */
class TicketServiceImplTest {

    TicketService ticketService = new TicketServiceImpl();
    @Test
    void addTicket() {
        try {
            ticketService.addTicket(new Ticket(5,5,new Date(),3));
        } catch (MovieException e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteTicket() {
        ticketService.deleteTicket(6);
    }

    @Test
    void alterTicket() {
        try {
            ticketService.alterTicket(new Ticket(8,5,5,new Date(),4));
        } catch (MovieException e) {
            e.printStackTrace();
        }
    }

    @Test
    void findTicketById() {
        Ticket ticket = null;
        try {
            ticket = ticketService.findTicketById(2);
        } catch (MovieException e) {
            e.printStackTrace();
        }
        System.out.println(ticket);
    }

    @Test
    void query() {
        List<Ticket> query = ticketService.query();
        for (Ticket ticket : query) {
            System.out.println(ticket);
        }
    }

    @Test
    void findTicketByUid() {
        try {
            List<Ticket> tickets = ticketService.findTicketByUid(5);
            for (Ticket ticket : tickets) {
                System.out.println(ticket);
            }
        } catch (MovieException e) {
            e.printStackTrace();
        }
    }

    @Test
    void findTicketBySid() {
        try {
            List<Ticket> tickets = ticketService.findTicketBySid(5);
            for (Ticket ticket : tickets) {
                System.out.println(ticket);
            }
        } catch (MovieException e) {
            e.printStackTrace();
        }
    }

    @Test
    void buyTicket() {
        try {
            ticketService.buyTicket(1,5,26);
        } catch (MovieException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getSeats() {
        try {
            List<Integer> seats = ticketService.getSeats(5);
            for (Integer i:seats){
                System.out.print(i+"\t");
            }
        } catch (MovieException e) {
            e.printStackTrace();
        }
    }
}