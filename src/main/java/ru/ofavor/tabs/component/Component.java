package ru.ofavor.tabs.component;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Component {
    private SimpleIntegerProperty article;
    private SimpleIntegerProperty price;
    private SimpleStringProperty type;

    public Component(int article, int price, String type) {
        this.article = new SimpleIntegerProperty(article);
        this.price = new SimpleIntegerProperty(price);
        this.type = new SimpleStringProperty(type);
    }

    public int getArticle() {
        return article.get();
    }

    public int getPrice() {
        return price.get();
    }

    public String getType() {
        return type.get();
    }

    public void setArticle(int article) {
        this.article = new SimpleIntegerProperty(article);
    }

    public void setPrice(int price) {
        this.price = new SimpleIntegerProperty(price);
    }

    public void setType(String type) {
        this.type = new SimpleStringProperty(type);
    }
}
