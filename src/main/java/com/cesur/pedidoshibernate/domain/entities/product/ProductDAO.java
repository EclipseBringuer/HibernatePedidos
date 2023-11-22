package com.cesur.pedidoshibernate.domain.entities.product;

import com.cesur.pedidoshibernate.domain.DAO;
import com.cesur.pedidoshibernate.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements DAO<Product> {
    @Override
    public List<Product> getAll() {
        List<Product> salida = new ArrayList<>();
        try (Session s = HibernateUtil.getSessionFactory().openSession()){
            Query<Product> q = s.createQuery("FROM Product ", Product.class);
            try {
                salida = q.getResultList();
            }catch (Exception e){
                System.out.println("ERROR: " + e.getMessage());
            }
        }
        return salida;
    }

    @Override
    public Product get(Long id) {
        return null;
    }

    @Override
    public Product save(Product data) {
        return null;
    }

    @Override
    public void update(Product data) {

    }

    @Override
    public void delete(Product data) {

    }
}
