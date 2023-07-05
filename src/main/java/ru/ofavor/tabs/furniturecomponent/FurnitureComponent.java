package ru.ofavor.tabs.furniturecomponent;

import javafx.beans.property.SimpleIntegerProperty;

public class FurnitureComponent {
    private SimpleIntegerProperty furnitureId;
    private SimpleIntegerProperty componentId;
    private SimpleIntegerProperty quantity;

    public FurnitureComponent(int furnitureId, int componentId, int quantity) {
        this.furnitureId = new SimpleIntegerProperty(furnitureId);
        this.componentId = new SimpleIntegerProperty(componentId);
        this.quantity = new SimpleIntegerProperty(quantity);
    }

    public int getComponentId() {
        return componentId.get();
    }

    public int getFurnitureId() {
        return furnitureId.get();
    }

    public int getQuantity() {
        return quantity.get();
    }
}
