package com.cesur.pedidoshibernate.domain.entities.item;

import com.cesur.pedidoshibernate.domain.entities.order.Order;
import com.cesur.pedidoshibernate.domain.entities.product.Product;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "item")
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cantidad")
    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "codigo_pedido", referencedColumnName = "codigo")
    private Order order;

    @OneToOne
    @JoinColumn(name = "id_producto")
    private Product product;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", amount=" + amount +
                ", order=" + order.getCode() +
                ", product=" + product +
                '}';
    }
}
