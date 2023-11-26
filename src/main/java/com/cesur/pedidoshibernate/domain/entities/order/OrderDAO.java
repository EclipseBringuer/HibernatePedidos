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
        Order exit = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();
            data.setCode(generateNewCode());
            s.persist(data);
            t.commit();
            exit = data;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return exit;
    }

    @Override
    public void update(Order data) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();
            s.merge(data);
            t.commit();
        }
    }

    @Override
    public void delete(Order data) {
        //Solo funciona con lamda. Preguntar a Francisco
        HibernateUtil.getSessionFactory().inTransaction(s -> {
            Order order = s.get(Order.class, data.getId());
            s.remove(order);
        });
    }

    private String generateNewCode() {
        String newCode = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            String lastCode = "";
            Query<String> q = s.createQuery("SELECT max(o.code) from Order o", String.class);
            lastCode = q.getSingleResult();
            int newNumber = 1;
            if (lastCode != null) {
                String numberStr = lastCode.substring(4);
                newNumber = Integer.parseInt(numberStr) + 1;
            }
            newCode = String.format("PED-%03d",newNumber);
        }
        return newCode;
    }
}
