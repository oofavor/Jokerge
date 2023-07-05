package ru.ofavor.auth;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ru.ofavor.App;
import ru.ofavor.Database;

public class RegisterController  {
    RegisterDetails details = new RegisterDetails();

    @FXML private PasswordField passwordField;
    @FXML private TextField emailField;
    @FXML private TextField faxField;
    @FXML private CheckBox isFactory;
    @FXML private CheckBox isStore;


    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("auth/login");
    }

    @FXML
    private void register() throws SQLException, IOException {
        details.setPassword(passwordField.getText());
        details.setEmail(emailField.getText());
        details.setFax(faxField.getText());
        details.setFactory(isFactory.isSelected());
        details.setStore(isStore.isSelected());

//        if (!details.validate()) return;
        if (!details.register()) return;

        App.setRoot("main");
    }
}
