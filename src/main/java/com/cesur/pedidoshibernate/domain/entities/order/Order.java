package com.cesur.pedidoshibernate.domain.entities.order;

import com.cesur.pedidoshibernate.domain.entities.item.Item;
import com.cesur.pedidoshibernate.domain.entities.user.User;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedido")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codigo")
    @GeneratedValue
    private String code;

    @Column(name = "fecha")
    private String date;

    @Column(name = "total")
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private User user;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<Item> items = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", date='" + date + '\'' +
                ", price=" + price +
                ", user=" + user.getName() +
                ", items=" + items +
                '}';
    }
}
