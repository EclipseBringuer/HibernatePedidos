module com.cesur.pedidoshibernate {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires java.naming;
    requires org.hibernate.orm.core;
    requires java.sql;


    opens com.cesur.pedidoshibernate.controllers to javafx.fxml;
    opens com.cesur.pedidoshibernate to javafx.fxml;
    opens com.cesur.pedidoshibernate.domain.entities.user;

    exports com.cesur.pedidoshibernate;
    exports com.cesur.pedidoshibernate.controllers;
}