package com.cesur.pedidoshibernate.controllers;

import com.cesur.pedidoshibernate.domain.entities.product.Product;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ProductWrapper extends VBox {
    private Product product;

    public ProductWrapper(Product product) {
        this.product = product;
        ImageView image = new ImageView(new Image(this.product.getImage()));
        image.setFitHeight(100);
        image.setFitWidth(100);
        Label title = new Label(this.product.getName());
        Label price = new Label(this.product.getPrice());
        //Label description = new Label(this.product.getDescription());
        this.getChildren().addAll(image, title, price/* description*/);
        this.setPrefSize(200, 100);
        this.getStyleClass().add("contenedor");
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
