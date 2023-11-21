package com.cesur.pedidoshibernate.controllers;

import com.cesur.pedidoshibernate.App;
import com.cesur.pedidoshibernate.domain.entities.user.User;
import com.cesur.pedidoshibernate.domain.entities.user.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class RegistController implements Initializable {
    @javafx.fxml.FXML
    private Label info;
    @javafx.fxml.FXML
    private TextField txtNombre;
    @javafx.fxml.FXML
    private TextField txtCorreo;
    @javafx.fxml.FXML
    private PasswordField txtPass;
    @javafx.fxml.FXML
    private Button btnVolver;
    @javafx.fxml.FXML
    private Button btnRegist;

    @javafx.fxml.FXML
    public void volver(ActionEvent actionEvent) {
        App.changeSecene("login-view.fxml");
    }

    @javafx.fxml.FXML
    public void registrarUsuario(ActionEvent actionEvent) {
        if (txtCorreo.getText().contains("@gmail.com")) {
            if (!Objects.equals(txtNombre.getText(), "") && !Objects.equals(txtCorreo.getText(), "") && !Objects.equals(txtPass.getText(), "")) {
                User user = new User();
                user.setName(txtNombre.getText());
                user.setEmail(txtCorreo.getText());
                user.setPassword(txtPass.getText());
                var userDAO = new UserDAO();
                try {
                    User salida = userDAO.save(user);
                    if (salida != null) {
                        App.changeSecene("login-view.fxml");
                    } else {
                        info.setText("Error, fallo al registrate");
                    }
                } catch (RuntimeException e) {
                    info.setText("Usuario ya existente");
                }
            } else {
                info.setText("Rellena todos los campos");
            }
        } else {
            info.setText("Correo invalido");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}