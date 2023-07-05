package ru.ofavor.tabs.order;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.ObjectProperty;

import java.sql.Date;

public class Order {
    private SimpleIntegerProperty article;
    private SimpleStringProperty storeFax;
    private SimpleObjectProperty date;

    public Order(int article, String storeFax, Date date) {
        this.article = new SimpleIntegerProperty(article);
        this.storeFax = new SimpleStringProperty(storeFax);
        this.date = new SimpleObjectProperty(date);
    }

    public int getArticle() {
        return article.get();
    }

    public String getStoreFax() {
        return storeFax.get();
    }

    public void setArticle(int article) {
        this.article = new SimpleIntegerProperty(article);
    }

    public void setStoreFax(String storeFax) {
        this.storeFax = new SimpleStringProperty(storeFax);
    }

    public Date getDate() {
        return (Date) date.get();
    }

    public void setDate(Object date) {
        this.date.set(date);
    }
}
