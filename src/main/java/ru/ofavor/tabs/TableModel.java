package ru.ofavor.tabs;

import javafx.beans.property.SimpleStringProperty;

public class TableModel {
    SimpleStringProperty a;
    SimpleStringProperty b;

    public TableModel(String a, String b) {
        this.a= new SimpleStringProperty(a);
        this.b=new SimpleStringProperty(b);
    }

    public String getA() {
        return a.get();
    }

    public String getB() {
        return b.get();
    }

    public void setA(String a) {
        this.a.set(a);
    }

    public void setB(String b) {
        this.b.set(b);
    }
}
