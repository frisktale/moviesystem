package com.frisk.dao.impl;

import com.frisk.dao.TicketDao;
import com.frisk.dao.db.DBUtils;
import com.frisk.entity.Ticket;
import com.frisk.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * User: frisk
 * Date: 2018/7/28
 * Time: 15:36
 */
public class TicketDaoImpl implements TicketDao {

    @Override
    public void insert(Ticket ticket) throws SQLException {
        DBUtils.execute("INSERT INTO ticket(id, uId, sId, time, seat) VALUES (?, ?, ?, ?, ?)",
                ticket.getId(), ticket.getuId(), ticket.getsId(), ticket.getTime(), ticket.getSeat());
    }

    @Override
    public void delete(int id) throws SQLException {
        DBUtils.execute("delete from ticket where id = ?", id);
    }

    @Override
    public void alter(Ticket ticket) throws SQLException {
        DBUtils.execute("update ticket set uId=?,sId=?,time=?,seat=? where id=?",
                ticket.getuId(), ticket.getsId(), ticket.getTime(), ticket.getSeat(), ticket.getId());
    }

    @Override
    public Ticket query(int id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<Ticket> query = DBUtils.query(Ticket.class, "select * from ticket where id = ?", id);
        if (query.isEmpty()) {
            return null;
        } else {
            return query.get(0);
        }
    }

    @Override
    public List<Ticket> queryByUid(int id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<Ticket> query = DBUtils.query(Ticket.class, "select * from ticket where uId = ?", id);
        return query;

    }

    @Override
    public List<Ticket> queryBySid(int id) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<Ticket> query = DBUtils.query(Ticket.class, "select * from ticket where sId = ?", id);
        return query;
    }

    @Override
    public List<Ticket> query() throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<Ticket> query = DBUtils.query(Ticket.class, "select * from ticket");
        return query;
    }
}
