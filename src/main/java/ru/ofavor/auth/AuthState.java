package ru.ofavor.auth;

public class AuthState {

    private static AuthState instance;
    private boolean isLogged = false;
    private boolean isFactory = false;
    private boolean isStore = false;
    private String fax ;

    public static synchronized AuthState getInstance() {
        if (instance == null) {
            instance = new AuthState();
        }
        return instance;
    }

    public boolean isFactory() {
        return isFactory;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public boolean isStore() {
        return isStore;
    }

    public void setFactory(boolean factory) {
        isFactory = factory;
    }

    public void setStore(boolean store) {
        isStore = store;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }
}
