package ru.ofavor.tabs.orderfurniture;

import javafx.beans.property.SimpleIntegerProperty;

public class OrderFurniture {
    private SimpleIntegerProperty furnitureId;
    private SimpleIntegerProperty orderId;
    private SimpleIntegerProperty quantity;

    public OrderFurniture(int furnitureId, int orderId, int quantity) {
        this.furnitureId = new SimpleIntegerProperty(furnitureId);
        this.orderId = new SimpleIntegerProperty(orderId);
        this.quantity = new SimpleIntegerProperty(quantity);
    }

    public int getOrderId() {
        return orderId.get();
    }

    public int getFurnitureId() {
        return furnitureId.get();
    }

    public int getQuantity() {
        return quantity.get();
    }
}
