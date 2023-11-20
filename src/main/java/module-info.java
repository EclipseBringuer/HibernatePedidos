module com.cesur.pedidoshibernate {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.cesur.pedidoshibernate to javafx.fxml;
    exports com.cesur.pedidoshibernate;
}