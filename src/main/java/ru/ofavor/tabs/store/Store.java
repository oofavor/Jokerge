package ru.ofavor.tabs.store;

import javafx.beans.property.SimpleStringProperty;

public class Store {
    private SimpleStringProperty fax;
    private SimpleStringProperty address;

    public Store(String fax, String address) {
        this.fax = new SimpleStringProperty(fax);
        this.address = new SimpleStringProperty(address);
    }

    public String getAddress() {
        return address.get();
    }

    public String getFax() {
        return fax.get();
    }

    public void setAddress(String address) {
        this.address = new SimpleStringProperty(address);
    }

    public void setFax(String fax) {
        this.fax = new SimpleStringProperty(fax);
    }
}
