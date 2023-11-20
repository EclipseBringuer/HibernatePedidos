package com.cesur.pedidoshibernate.domain.entities.item;

import com.cesur.pedidoshibernate.domain.entities.order.Order;
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

}
