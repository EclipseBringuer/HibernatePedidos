package com.cesur.pedidoshibernate.domain.entities.user;

import com.cesur.pedidoshibernate.domain.DAO;
import com.cesur.pedidoshibernate.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class UserDAO implements DAO<User> {
    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User get(Long id) {
        return null;
    }

    @Override
    public User save(User data) {
        User salida = null;
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();
            s.persist(data);
            t.commit();
            salida = data;
        } catch (Exception e) {
            System.out.println("Fallo al guardar: " + e.getMessage());
        }
        return salida;
    }

    @Override
    public void update(User data) {

    }

    @Override
    public void delete(User data) {

    }

    public User validateUser(String email, String password) {
        User result = null;

        try (Session s = HibernateUtil.getSessionFactory().openSession()) {

            Query<User> q = s.createQuery("FROM User u WHERE u.email=:em AND u.password=:pass", User.class);
            q.setParameter("em", email);
            q.setParameter("pass", password);

            try {
                result = q.getSingleResult();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return result;
    }
}
