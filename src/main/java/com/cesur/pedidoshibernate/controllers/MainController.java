package com.cesur.pedidoshibernate.controllers;

import com.cesur.pedidoshibernate.App;
import com.cesur.pedidoshibernate.Session;
import com.cesur.pedidoshibernate.domain.entities.item.Item;
import com.cesur.pedidoshibernate.domain.entities.order.Order;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @javafx.fxml.FXML
    private HBox userMenu;
    @javafx.fxml.FXML
    private Label txtUsuario;
    @javafx.fxml.FXML
    private ImageView imgSalir;
    @javafx.fxml.FXML
    private TableView<Order> table;
    @javafx.fxml.FXML
    private TableColumn<Order, String> columnCodigo;
    @javafx.fxml.FXML
    private TableColumn<Order, String> columnFecha;
    @javafx.fxml.FXML
    private TableColumn<Order, String> columnPrecio;
    @javafx.fxml.FXML
    private Label infoPedido;
    @javafx.fxml.FXML
    private TableView<Item> tableItem;
    @javafx.fxml.FXML
    private TableColumn<Item, String> columnProducto;
    @javafx.fxml.FXML
    private TableColumn<Item, String> columnPrecioProducto;
    @javafx.fxml.FXML
    private TableColumn<Item, String> columnCantidad;

    @javafx.fxml.FXML
    public void logout(Event event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText("¿Estas seguro de que quieres salir?");
        alert.setContentText("Presiona aceptar para cerrar sesión");
        alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
        alert.showAndWait().ifPresent((response) -> {
            if (response == ButtonType.OK) {
                Session.setCurrentUser(null);
                Session.setCurrentOrder(null);
                App.changeScene("login-view.fxml");
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configuración inicial de la vista
        txtUsuario.setText(txtUsuario.getText() + Session.getCurrentUser().getName());
        ObservableList<Item> items = tableItem.getItems();

        table.getItems().addAll(Session.getCurrentUser().getOrders());

        // Configuración de las columnas de la tabla de pedidos
        columnFecha.setCellValueFactory((fila) -> {
            String fecha = fila.getValue().getDate();
            return new SimpleStringProperty(fecha);
        });

        columnCodigo.setCellValueFactory((fila) -> {
            String codigo = fila.getValue().getCode();
            return new SimpleStringProperty(codigo);
        });

        columnPrecio.setCellValueFactory((fila) -> {
            String total = fila.getValue().getPrice() + " €";
            return new SimpleStringProperty(total);
        });
        // Manejo de selección de un pedido en la tabla
        table.getSelectionModel().selectedItemProperty().addListener(
                (observable, vOld, vNew) -> {
                    Session.setCurrentOrder(table.getSelectionModel().getSelectedItem());
                    items.clear();
                    infoPedido.setText("Información del pedido: " + Session.getCurrentOrder().getCode());
                    items.addAll(Session.getCurrentOrder().getItems());
                }
        );

        // Configuración de las columnas de la tabla de ítems en el pedido
        columnCantidad.setCellValueFactory((fila) -> {
            String fecha = fila.getValue().getAmount() + "";
            return new SimpleStringProperty(fecha);
        });

        columnProducto.setCellValueFactory((fila) -> {
            String codigo = fila.getValue().getProduct().getName();
            return new SimpleStringProperty(codigo);
        });

        columnPrecioProducto.setCellValueFactory((fila) -> {
            String total = fila.getValue().getProduct().getPrice();
            return new SimpleStringProperty(total);
        });

    }
}