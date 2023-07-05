package ru.ofavor.tabs.furniture;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Furniture {
    private SimpleIntegerProperty article;
    private SimpleIntegerProperty price;
    private SimpleStringProperty type;
    private SimpleStringProperty line;

    public Furniture(int article, int price, String type, String line) {
        this.article = new SimpleIntegerProperty(article);
        this.price = new SimpleIntegerProperty(price);
        this.type = new SimpleStringProperty(type);
        this.line = new SimpleStringProperty(line);
    }

    public int getArticle() {
        return article.get();
    }

    public int getPrice() {
        return price.get();
    }

    public String getLine() {
        return line.get();
    }

    public String getType() {
        return type.get();
    }

    public void setArticle(int article) {
        this.article = new SimpleIntegerProperty(article);
    }

    public void setLine(String line) {
        this.line = new SimpleStringProperty(line);
    }

    public void setPrice(int price) {
        this.price = new SimpleIntegerProperty(price);
    }

    public void setType(String type) {
        this.type = new SimpleStringProperty(type);
    }
}
