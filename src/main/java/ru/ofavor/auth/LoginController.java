package ru.ofavor.auth;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ru.ofavor.App;
import ru.ofavor.Database;

public class LoginController   {
    private LoginDetails details = new LoginDetails();

    @FXML private PasswordField passwordField;

    @FXML private TextField emailField;

    @FXML
    private void switchToRegister() throws IOException {
        App.setRoot("auth/register");
    }

    @FXML
    private void login() throws IOException, SQLException {
        details.setPassword(passwordField.getText());
        details.setEmail(emailField.getText());

//        if (!details.validate()) return;
        if (!details.login()) return;

        App.setRoot("secondary");
    }

    @FXML
    private void loginGuest() throws IOException {
        App.setRoot("main");
    }

}
