package com.cesur.pedidoshibernate.controllers;

import com.cesur.pedidoshibernate.App;
import com.cesur.pedidoshibernate.Session;
import com.cesur.pedidoshibernate.domain.entities.item.Item;
import com.cesur.pedidoshibernate.domain.entities.order.Order;
import com.cesur.pedidoshibernate.domain.entities.order.OrderDAO;
import com.cesur.pedidoshibernate.domain.entities.product.Product;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

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
    @javafx.fxml.FXML
    private TableView<Item> table;
    @javafx.fxml.FXML
    private TableColumn<Item, String> cName;
    @javafx.fxml.FXML
    private TableColumn<Item, String> cPrice;
    @javafx.fxml.FXML
    private TableColumn<Item, String> cAmount;
    @javafx.fxml.FXML
    private Button btnReturn;
    @javafx.fxml.FXML
    private TableColumn<Item, Void> cDelete;
    @javafx.fxml.FXML
    private Label labelPed;
    @javafx.fxml.FXML
    private Button btnAddItem;
    @javafx.fxml.FXML
    private Button btnSaveOrder;
    @javafx.fxml.FXML
    private Label labelTotal;
    @javafx.fxml.FXML
    private Spinner<Integer> units;
    @javafx.fxml.FXML
    private Label labelUnits;
    @javafx.fxml.FXML
    private Label labelName;
    private Product currentProduct = null;
    private ObservableList<Item> items;
    @javafx.fxml.FXML
    private ImageView image;
    private Order newOrder;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Vinculacion de el observable y la tabla
        items = table.getItems();

        //Configuracion del spinner y su label
        labelUnits.setText(labelUnits.getText() + " 0 unidades: 0 €");
        units.valueProperty().addListener((observable, oldValue, newValue) -> {
            labelUnits.setText("Precio con " + newValue + " unidades: " + currentProduct.getPrice() * newValue + " €");
        });
        //

        items.addAll(Session.getCurrentOrder().getItems());
        labelPed.setText(labelPed.getText() + " " + Session.getCurrentOrder().getCode());
        btnSaveOrder.setText("Guardar cambios");
        updateTotal(items);

        cName.setCellValueFactory((row) -> {
            String name = row.getValue().getProduct().getName();
            return new SimpleStringProperty(name);
        });

        cAmount.setCellValueFactory((row) -> {
            String amount = row.getValue().getAmount() + "";
            return new SimpleStringProperty(amount);
        });

        cPrice.setCellValueFactory((row) -> {
            String price = row.getValue().getProduct().getPrice() + " €";
            return new SimpleStringProperty(price);
        });

        cDelete.setCellFactory(param -> new TableCell<Item, Void>() {
            private final Button btnEliminar = new Button();

            {
                // Configurar imágenes para botones
                ImageView eliminarImage = new ImageView(new Image("C:\\Users\\gabri\\IdeaProjects\\PedidosHibernate\\src\\main\\resources\\img\\borrar.png"));
                eliminarImage.setFitWidth(25);
                eliminarImage.setFitHeight(25);
                btnEliminar.setGraphic(eliminarImage);
                //Acción de eliminar item
                btnEliminar.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmación");
                    alert.setHeaderText("¿Estas seguro de que quieres borrar el producto " + table.getItems().
                            get(getIndex()).getProduct().getName() + "?");
                    alert.setContentText("Presiona aceptar para borrarlo");
                    alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
                    alert.showAndWait().ifPresent((response) -> {
                        if (response == ButtonType.OK) {
                            items.remove(table.getItems().get(getIndex()));
                            updateTotal(items);
                        }
                    });
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    // Añadir botones a la celda
                    HBox hbox = new HBox(btnEliminar);
                    hbox.setSpacing(20);
                    setGraphic(hbox);
                }
            }
        });


        txtUsuario.setText(txtUsuario.getText() + Session.getCurrentUser().getName());

        for (Product p : Session.getProductsList()) {
            makeProductViewer(p);
        }
    }

    //Metodo para renderizar los productos de la lista
    private void makeProductViewer(Product p) {
        ProductWrapper pw = new ProductWrapper(p);
        pw.setOnMouseClicked(event -> {
            currentProduct = pw.getProduct();
            units.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, p.getAmount(), 1, 1));
            labelUnits.setText("Precio con 1 unidades: " + currentProduct.getPrice() + " €");
            labelName.setText(" " + p.getName());
            image.setImage(new Image(pw.getProduct().getImage()));
        });
        container.getChildren().add(pw);
    }

    //Metodo para hacer logout
    @javafx.fxml.FXML
    public void logout(Event event) {
        App.logout();
    }

    //Metodo para volver a la pantalla main
    @javafx.fxml.FXML
    public void returnToMain(ActionEvent actionEvent) {
        if (Session.getCurrentOrder().getItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText("El pedido " + Session.getCurrentOrder().getCode()
                    + " está vacio ¿Estas seguro de que quieres salir?");
            alert.setContentText("Si pulsas salir se borrará el pedido");
            alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
            alert.showAndWait().ifPresent((response) -> {
                if (response == ButtonType.OK) {
                    var miDao = new OrderDAO();
                    miDao.delete(Session.getCurrentOrder());
                    Session.getCurrentOrderList().remove(Session.getCurrentOrder());
                    Session.setCurrentOrder(null);
                    App.changeScene("main-view.fxml");
                }
            });
        } else {
            Session.setCurrentOrder(null);
            App.changeScene("main-view.fxml");
        }
    }

    //Metodo para actualizar el contador de precio total
    private Integer updateTotal(List<Item> items) {
        int precioTotal = 0;
        for (Item i : items) {
            precioTotal += i.getProduct().getPrice() * i.getAmount();
        }
        labelTotal.setText("Total: " + precioTotal + " €");
        return precioTotal;
    }

    //Metodo para añadir un item a la tabla
    @javafx.fxml.FXML
    public void addItem(ActionEvent actionEvent) {
        if (currentProduct != null) {
            Item item = new Item();
            item.setOrder(Session.getCurrentOrder());
            item.setAmount(units.getValue());
            item.setProduct(currentProduct);
            boolean found = false;
            for (Item i : items) {
                if (i.getProduct().equals(item.getProduct())) {
                    found = true;
                    i.setAmount(i.getAmount() + units.getValue());
                    table.refresh();
                }
            }
            if (!found) {
                items.add(item);
                table.refresh();
            }
            updateTotal(items);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error al añadir producto");
            alert.setHeaderText("No hay un producto seleccionado");
            alert.setContentText("Pulsa aceptar para volver");
            alert.showAndWait();
        }

    }

    @javafx.fxml.FXML
    public void saveOrder(ActionEvent actionEvent) {
        var orderDao = new OrderDAO();
        Session.getCurrentOrder().setItems(items);
        Session.getCurrentOrder().setPrice(updateTotal(items));
        orderDao.update(Session.getCurrentOrder());
    }
}