package com.cesur.pedidoshibernate.domain;

import java.util.ArrayList;

/**
 * DAO general para todas las clases
 *
 * @param <T> parametro que representa la entidad que queramos en la devolucion, Usuario,Juegos...
 */
public interface DAO<T> {

    public ArrayList<T> getAll();

    public T get(Long id);

    public T save(T data);

    public void update(T data);

    public void delete(T data);

}
