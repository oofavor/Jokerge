package ru.ofavor.tabs.orderfurniture;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.ofavor.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderFurnitureList {
    private ObservableList<OrderFurniture> items = FXCollections.observableArrayList();

    public ObservableList<OrderFurniture> getItems() {
        return items;
    }

    public void fetch() throws SQLException {
        items.clear();
        String query = "SELECT furnitureId, orderId, quantity FROM OrderFurniture";
        PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(query);

        ResultSet result;
        try {
            result = statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        while (result.next()) {
            int furnitureId = result.getInt("furnitureId");
            int orderId = result.getInt("orderId");
            int quantity = result.getInt("quantity");

            OrderFurniture newOrderFurniture = new OrderFurniture(furnitureId,orderId,quantity);

            items.add(newOrderFurniture);
        }
    }

    public void add(OrderFurniture orderFurniture) throws SQLException {
        String query = "INSERT INTO OrderFurniture (furnitureId, orderId, quantity) VALUES (?, ?, ?)";
        PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(query);

        statement.setInt(1, orderFurniture.getFurnitureId());
        statement.setInt(2, orderFurniture.getOrderId());
        statement.setInt(3, orderFurniture.getQuantity());

        try {
            statement.executeUpdate();
            items.add(orderFurniture);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int itemId) throws SQLException {
        int furnitureId = items.get(itemId).getFurnitureId();
        int orderId = items.get(itemId).getOrderId();
        String query = "DELETE FROM OrderFurniture WHERE furnitureId=? AND orderId=?";

        PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(query);

        statement.setInt(1, furnitureId);
        statement.setInt(2, orderId);

        try {
            statement.executeUpdate();
            items.remove(itemId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(int itemId, Integer furnitureId, Integer orderId, Integer quantity ) throws SQLException {
        int i = 0;

        String setString = "SET ";
        if (furnitureId != null) setString += "furnitureId=?,";
        if (orderId != null) setString += "orderId=?,";
        if (quantity != null) setString += "quantity=?,";

        setString = setString.replaceAll(",$", "\n");

        String query = "UPDATE OrderFurniture " + setString + "WHERE furnitureId=? AND orderId=?;";

        PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(query);

        if (furnitureId != null) statement.setInt(++i, furnitureId);
        if (orderId != null) statement.setInt(++i, orderId);
        if (quantity != null) statement.setInt(++i, quantity);

        int furnitureId1 = items.get(itemId).getFurnitureId();
        int componentId1 = items.get(itemId).getOrderId();
        statement.setInt(++i, furnitureId1);
        statement.setInt(++i, componentId1);

        try {
            statement.executeUpdate();
            fetch();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
