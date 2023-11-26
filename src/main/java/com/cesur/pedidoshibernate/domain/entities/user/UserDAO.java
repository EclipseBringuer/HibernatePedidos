package com.cesur.pedidoshibernate.domain.entities.user;

import com.cesur.pedidoshibernate.domain.DAO;
import com.cesur.pedidoshibernate.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

/**
 * La clase UserDAO implementa la interfaz DAO para la entidad User.
 * Proporciona métodos para realizar operaciones CRUD en la base de datos para usuarios.
 */
public class UserDAO implements DAO<User> {
    /**
     * Obtiene una lista de todos los usuarios. (No implementado en este caso)
     *
     * @return Lista de usuarios (en este caso, se devuelve null).
     */
    @Override
    public List<User> getAll() {
        return null;
    }

    /**
     * Obtiene un usuario basado en su identificador único. (No implementado en este caso)
     *
     * @param id El identificador único del usuario.
     * @return El usuario correspondiente al identificador proporcionado (en este caso, se devuelve null).
     */
    @Override
    public User get(Long id) {
        return null;
    }

    /**
     * Guarda un nuevo usuario en la base de datos.
     *
     * @param data El usuario a ser guardado.
     * @return El usuario guardado con posiblemente valores actualizados.
     */
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

    /**
     * Actualiza los datos de un usuario en la base de datos. (No implementado en este caso)
     *
     * @param data El usuario a ser actualizado.
     */
    @Override
    public void update(User data) {

    }

    /**
     * Elimina un usuario de la base de datos. (No implementado en este caso)
     *
     * @param data El usuario a ser eliminado.
     */
    @Override
    public void delete(User data) {

    }

    /**
     * Valida las credenciales de un usuario en la base de datos.
     *
     * @param email    El correo electrónico del usuario.
     * @param password La contraseña del usuario.
     * @return El usuario validado, o null si las credenciales no son válidas.
     */
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
