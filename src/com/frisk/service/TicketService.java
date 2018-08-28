package com.frisk.service;

import com.frisk.MovieException;
import com.frisk.entity.Ticket;

import java.util.List;

/**
 * User: frisk
 * Date: 2018/7/29
 * Time: 11:15
 */
public interface TicketService {
    public void addTicket(Ticket ticket) throws MovieException;
    public void deleteTicket(int id);
    public void alterTicket(Ticket ticket) throws MovieException;
    public Ticket findTicketById(int id) throws MovieException;
    public List<Ticket> query();
    public List<Ticket> findTicketByUid(int id) throws MovieException;
    public List<Ticket> findTicketBySid(int id) throws MovieException;

    public void buyTicket(int uId,int sId,int seat) throws MovieException;

    public List<Integer> getSeats(int sId) throws MovieException;
}
