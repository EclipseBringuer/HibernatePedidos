package com.cesur.pedidoshibernate.domain.entities.order;

import com.cesur.pedidoshibernate.domain.DAO;
import com.cesur.pedidoshibernate.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Queue;

/**
 * La clase OrderDAO implementa la interfaz DAO para la entidad Order.
 * Proporciona métodos para realizar operaciones CRUD en la base de datos para pedidos.
 */
public class OrderDAO implements DAO<Order> {
    /**
     * Obtiene una lista de todos los pedidos. (No implementado en este caso)
     *
     * @return Lista de pedidos (en este caso, se devuelve null).
     */
    @Override
    public List<Order> getAll() {
        return null;
    }

    /**
     * Obtiene un pedido basado en su identificador único. (No implementado en este caso)
     *
     * @param id El identificador único del pedido.
     * @return El pedido correspondiente al identificador proporcionado (en este caso, se devuelve null).
     */
    @Override
    public Order get(Long id) {
        return null;
    }

    /**
     * Guarda un nuevo pedido en la base de datos.
     *
     * @param data El pedido a ser guardado.
     * @return El pedido guardado con posiblemente valores actualizados.
     */
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

    /**
     * Actualiza los datos de un pedido en la base de datos.
     *
     * @param data El pedido a ser actualizado.
     */
    @Override
    public void update(Order data) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();
            s.merge(data);
            t.commit();
        }
    }

    /**
     * Elimina un pedido de la base de datos.
     *
     * @param data El pedido a ser eliminado.
     */
    @Override
    public void delete(Order data) {
        //Solo funciona con lamda. Preguntar a Francisco
        HibernateUtil.getSessionFactory().inTransaction(s -> {
            Order order = s.get(Order.class, data.getId());
            s.remove(order);
        });
    }

    /**
     * Genera un nuevo código para un pedido basado en el último código existente en la base de datos.
     *
     * @return El nuevo código generado para el pedido.
     */
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
