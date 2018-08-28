package com.frisk.dao.test;

import com.frisk.MovieException;
import com.frisk.dao.TicketDao;
import com.frisk.dao.impl.TicketDaoImpl;
import com.frisk.entity.Ticket;
import com.frisk.utils.DateFormatByString;
import com.frisk.utils.impl.DateFormatByStringImpl;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * User: frisk
 * Date: 2018/7/28
 * Time: 16:30
 */
class TicketDaoTest {

    TicketDao ticketDao = new TicketDaoImpl();
    DateFormatByString format = new DateFormatByStringImpl();



    @Test
    void insert() {
        Ticket ticket = new Ticket(1,5,new Date(),6);
        try {
            ticketDao.insert(ticket);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void delete() {
        try {
            ticketDao.delete(3);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void alter() {
        Ticket ticket = new Ticket(2,1,5,new Date(),6);
        try {
            ticketDao.alter(ticket);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void query() {
        try {
            Ticket query = ticketDao.query(2);
            System.out.println(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    void queryByUid() {
        try {
            List<Ticket> tickets = ticketDao.queryByUid(1);
            for (Ticket t:tickets){
                System.out.println(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    void queryBySid() {
        try {
            List<Ticket> tickets = ticketDao.queryBySid(5);
            for (Ticket t:tickets){
                System.out.println(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    void query1() {
        try {
            List<Ticket> tickets = ticketDao.query();
            for (Ticket t:tickets){
                System.out.println(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}