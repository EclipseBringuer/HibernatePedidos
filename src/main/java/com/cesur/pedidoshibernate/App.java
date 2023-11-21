package com.cesur.pedidoshibernate;

import com.cesur.pedidoshibernate.domain.HibernateUtil;
import com.cesur.pedidoshibernate.domain.entities.user.User;
import com.cesur.pedidoshibernate.domain.entities.user.UserDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    private static Stage myStage;
    @Override
    public void start(Stage stage) throws IOException {
        myStage = stage;
        System.out.println(HibernateUtil.getSessionFactory());
        UserDAO userDAO = new UserDAO();
        User user = userDAO.validateUser("gabrielrl2004@gmail.com","15112004");
        System.out.println(user);
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        myStage.setTitle("CholloGaming");
        myStage.setMaximized(true);
        myStage.setScene(scene);
        myStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void changeScene(String fxml) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/" + fxml));
            Pane newPanel = fxmlLoader.load();
            myStage.getScene().setRoot(newPanel);
            myStage.setMaximized(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}