package com.cesur.pedidoshibernate;

import com.cesur.pedidoshibernate.domain.entities.order.Order;
import com.cesur.pedidoshibernate.domain.entities.product.Product;
import com.cesur.pedidoshibernate.domain.entities.user.User;

import java.util.ArrayList;
import java.util.List;

public class Session {
    private static User currentUser = null;
    private static Order currentOrder = null;
    private static List<Product> productsList = new ArrayList<>();
    private static List<Order> currentOrderList = new ArrayList<>();

    public static User getCurrentUser() {

        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {

        Session.currentUser = currentUser;
    }

    public static Order getCurrentOrder() {

        return currentOrder;
    }

    public static void setCurrentOrder(Order currentOrder) {

        Session.currentOrder = currentOrder;
    }

    public static List<Product> getProductsList() {

        return productsList;
    }

    public static void setProductsList(List<Product> productsList) {

        Session.productsList = productsList;
    }

    public static List<Order> getCurrentOrderList() {
        return currentOrderList;
    }

    public static void setCurrentOrderList(List<Order> currentOrderList) {
        Session.currentOrderList = currentOrderList;
    }
}
