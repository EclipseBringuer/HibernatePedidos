package com.cesur.pedidoshibernate.domain.entities.order;

import com.cesur.pedidoshibernate.domain.DAO;
import com.cesur.pedidoshibernate.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Queue;

public class OrderDAO implements DAO<Order> {
    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public Order get(Long id) {
        return null;
    }

    @Override
    public Order save(Order data) {
        return null;
    }

    @Override
    public void update(Order data) {

    }

    @Override
    public void delete(Order data) {
        //Solo funciona con lamda. Preguntar a Francisco
        HibernateUtil.getSessionFactory().inTransaction(s -> {
            Order order = s.get(Order.class, data.getId());
            s.remove(order);
        });
    }
}
