package ru.ofavor.auth;

import ru.ofavor.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterDetails {
    private String email = "";
    private String password = "";
    private boolean isFactory;
    private boolean isStore;
    private String fax;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFactory(boolean factory) {
        isFactory = factory;
    }

    public void setStore(boolean store) {
        isStore = store;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }


    public boolean validate() {
        if (!AuthUtils.validatePassword(password) || !AuthUtils.validateEmail(email)) return false;
        if (isStore && fax.length() == 0) return false;

        return true;
    }

    public boolean register() throws SQLException {
        String query ;
        if (isStore) {
            query = "INSERT INTO Users (email, password, isFactory, isStore, fax) VALUES (?, ?, ?, ?, ?)";
        } else {
            query = "INSERT INTO Users (email, password, isFactory, isStore) VALUES (?, ?, ?, ?)";
        }

        PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(query);

        statement.setString(1, email);
        statement.setString(2, AuthUtils.encodePassword(password));
        statement.setBoolean(3, isFactory);
        statement.setBoolean(4, isStore);
        if (isStore) {
            statement.setString(5, fax);
        }

        try {
            int result = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        AuthState.getInstance().setFactory(isFactory);
        AuthState.getInstance().setStore(isStore);
        AuthState.getInstance().setLogged(true);
        if (isStore) {
            AuthState.getInstance().setFax(getFax());
        }

        return true;
    }
}
