package ru.ofavor.auth;

import ru.ofavor.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDetails   {
    private String email = "";
    private String password = "";

    public boolean validate() {
        if (!AuthUtils.validatePassword(password) || !AuthUtils.validateEmail(email)) return false;

        return true;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean login() throws SQLException {
        String query = "SELECT isFactory, isStore, fax FROM Users WHERE email=? AND password=?";
        PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(query);

        statement.setString(1, email);
        statement.setString(2, AuthUtils.encodePassword(password));

        ResultSet result = statement.executeQuery();

        if (!result.next()) return false;

        System.out.println("!@#!@#@#");
        boolean isFactory = result.getBoolean("isFactory");
        boolean isStore = result.getBoolean("isStore");

        AuthState.getInstance().setFactory(isFactory);
        AuthState.getInstance().setStore(isStore);
        AuthState.getInstance().setLogged(true);

        if (isStore) {
            AuthState.getInstance().setFax(result.getString("fax"));
        }

        return true;
    }
}
