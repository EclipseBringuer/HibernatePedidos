package com.cesur.pedidoshibernate.controllers;

import com.cesur.pedidoshibernate.App;
import com.cesur.pedidoshibernate.Session;
import com.cesur.pedidoshibernate.domain.entities.user.User;
import com.cesur.pedidoshibernate.domain.entities.user.UserDAO;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Label info;
    @FXML
    private TextField txtCorreo;
    @FXML
    private PasswordField txtPass;
    @FXML
    private Button btnLogin;
    @FXML
    private Label txtRegistrate;

    @FXML
    public void logear(ActionEvent actionEvent) {
        var userDAO = new UserDAO();
        User user = userDAO.validateUser(txtCorreo.getText(),txtPass.getText());
        if (user == null) {
            info.setText("Usuario o contraseña incorrecto");
        } else {
            Session.setCurrentUser(user);
            App.changeSecene("main-view.fxml");
        }
    }

    @FXML
    public void cargarRegistro(Event event) {
        App.changeSecene("regist-view.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtCorreo.setText("gabrielrl2004@gmail.com");
        txtPass.setText("15112004");
    }
}