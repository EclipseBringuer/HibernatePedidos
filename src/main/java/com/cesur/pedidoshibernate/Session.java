package com.cesur.pedidoshibernate;

import com.cesur.pedidoshibernate.domain.entities.order.Order;
import com.cesur.pedidoshibernate.domain.entities.user.User;

public class Session {
    private static User currentUser = null;
    private static Order currentOrder = null;

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
}
