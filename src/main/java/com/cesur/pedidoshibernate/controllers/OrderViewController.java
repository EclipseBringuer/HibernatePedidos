package com.cesur.pedidoshibernate.controllers;

import com.cesur.pedidoshibernate.App;
import com.cesur.pedidoshibernate.Session;
import com.cesur.pedidoshibernate.domain.entities.product.Product;
import com.cesur.pedidoshibernate.domain.entities.product.ProductDAO;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class OrderViewController implements Initializable {

    @javafx.fxml.FXML
    private HBox userMenu;
    @javafx.fxml.FXML
    private Label txtUsuario;
    @javafx.fxml.FXML
    private ImageView imgSalir;
    @javafx.fxml.FXML
    private FlowPane container;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtUsuario.setText(txtUsuario.getText() + Session.getCurrentUser().getName());
        var productDao = new ProductDAO();
        List<Product> products = productDao.getAll();
        for (Product p : products) {
            makeProductViewer(p);
        }
    }

    private void makeProductViewer(Product p) {
        ProductWrapper pw = new ProductWrapper(p);
        pw.setOnMouseClicked(event -> {
            System.out.println("Has clicado en el producto " + pw.getProduct().getName());
        });
        container.getChildren().add(pw);
    }

    @javafx.fxml.FXML
    public void logout(Event event) {
        App.logout();
    }
}