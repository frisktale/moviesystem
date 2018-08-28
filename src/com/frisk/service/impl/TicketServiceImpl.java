package com.frisk.service.impl;

import com.frisk.MovieException;
import com.frisk.dao.TicketDao;
import com.frisk.dao.impl.TicketDaoImpl;
import com.frisk.entity.Hall;
import com.frisk.entity.Session;
import com.frisk.entity.Ticket;
import com.frisk.entity.User;
import com.frisk.service.HallService;
import com.frisk.service.SessionService;
import com.frisk.service.TicketService;
import com.frisk.service.UserService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: frisk
 * Date: 2018/7/29
 * Time: 11:39
 */
public class TicketServiceImpl implements TicketService {
    TicketDao ticketDao = new TicketDaoImpl();

    @Override
    public void addTicket(Ticket ticket) throws MovieException {
        try {
            ticketDao.insert(ticket);
        } catch (SQLException e) {
            if (e.getMessage().contains("a foreign key constraint fails")) {
                throw new MovieException("用户或场次不存在");
            }
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTicket(int id) {
        try {
            ticketDao.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void alterTicket(Ticket ticket) throws MovieException {
        try {
            ticketDao.alter(ticket);
        } catch (SQLException e) {
            if (e.getMessage().contains("a foreign key constraint fails")) {
                throw new MovieException("输入的用户或场次编号不存在");
            }
            e.printStackTrace();
        }
    }

    @Override
    public Ticket findTicketById(int id) throws MovieException {
        Ticket query = null;
        try {
            query = ticketDao.query(id);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (query != null) {
            return query;
        } else {
            throw new MovieException("未找到电影票");
        }
    }

    @Override
    public List<Ticket> query() {
        List<Ticket> tickets = null;
        try {
            tickets = ticketDao.query();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    @Override
    public List<Ticket> findTicketByUid(int id) throws MovieException {
        List<Ticket> tickets = null;
        try {
            tickets = ticketDao.queryByUid(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (tickets.size() > 0) {
            return tickets;
        } else {
            throw new MovieException("未找到电影票");
        }
    }

    @Override
    public List<Ticket> findTicketBySid(int id) throws MovieException {
        List<Ticket> tickets = null;
        try {
            tickets = ticketDao.queryBySid(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (tickets.size() > 0) {
            return tickets;
        } else {
            throw new MovieException("未找到电影票");
        }
    }

    @Override
    public void buyTicket(int uId, int sId, int seat) throws MovieException {
        UserService userService = new UserServiceImpl();
        SessionService sessionService = new SessionServiceImpl();
        User user = userService.findUserById(uId);
        Session session = sessionService.findSessionById(sId);
        if (session.getCount() <= 0) {
            throw new MovieException("票已售完");
        }
        session.setCount(session.getCount() - 1);
        if (user.getMoney() < session.getPrice()) {
            throw new MovieException("余额不足,请及时充值");
        }
        Ticket ticket = new Ticket(uId, sId, new Date(), seat);
        user.setMoney(user.getMoney()-session.getPrice());
        userService.alterUser(user);
        sessionService.alterSession(session);
        addTicket(ticket);
    }

    @Override
    public List<Integer> getSeats(int sId) throws MovieException {
        System.out.println(sId);
        SessionService sessionService = new SessionServiceImpl();
        HallService hallService = new HallServiceImpl();
        Session session = sessionService.findSessionById(sId);
        Hall hall = hallService.findHallById(session.gethId());
        List<Integer> seats = new ArrayList<>();
        for (int i = 1; i <= hall.getCount(); i++) {
            seats.add(i);
        }
        List<Ticket> tickets = new ArrayList<>();
        try {
            tickets.addAll(findTicketBySid(sId));
        } catch (MovieException e) {

        }
        for (Ticket t : tickets) {
            seats.remove((Object) t.getSeat());
        }
        if (seats.size()<=0){
            throw new MovieException("座位已满");
        }
        return seats;
    }
}
