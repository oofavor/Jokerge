package ru.ofavor.tabs.furniturecomponent;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.ofavor.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FurnitureComponentList {
    private ObservableList<FurnitureComponent> items = FXCollections.observableArrayList();

    public ObservableList<FurnitureComponent> getItems() {
        return items;
    }

    public void fetch() throws SQLException {
        items.clear();
        String query = "SELECT furnitureId, componentId, quantity FROM FurnitureComponent";
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
            int componentId = result.getInt("componentId");
            int quantity = result.getInt("quantity");

            FurnitureComponent newFurnitureComponent = new FurnitureComponent(furnitureId,componentId,quantity);

            items.add(newFurnitureComponent);
        }
    }

    public void add(FurnitureComponent furnitureComponent) throws SQLException {
        String query = "INSERT INTO FurnitureComponent (furnitureId, componentId, quantity) VALUES (?, ?, ?)";
        PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(query);

        statement.setInt(1, furnitureComponent.getFurnitureId());
        statement.setInt(2, furnitureComponent.getComponentId());
        statement.setInt(3, furnitureComponent.getQuantity());

        try {
            statement.executeUpdate();
            items.add(furnitureComponent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int itemId) throws SQLException {
        int furnitureId = items.get(itemId).getFurnitureId();
        int componentId = items.get(itemId).getComponentId();
        String query = "DELETE FROM FurnitureComponent WHERE furnitureId=? AND componentId=?";
        PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(query);

        statement.setInt(1, furnitureId);
        statement.setInt(2, componentId);

        try {
            statement.executeUpdate();
            items.remove(itemId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(int itemId, Integer furnitureId, Integer componentId, Integer quantity ) throws SQLException {
        int i = 0;

        String setString = "SET ";
        if (furnitureId != null) setString += "furnitureId=?,";
        if (componentId != null) setString += "componentId=?,";
        if (quantity != null) setString += "quantity=?,";

        setString = setString.replaceAll(",$", "\n");

        String query = "UPDATE FurnitureComponent " + setString + "WHERE furnitureId=? AND componentId=?;";

        PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(query);

        if (furnitureId != null) statement.setInt(++i, furnitureId);
        if (componentId != null) statement.setInt(++i, componentId);
        if (quantity != null) statement.setInt(++i, quantity);

        int furnitureId1 = items.get(itemId).getFurnitureId();
        int componentId1 = items.get(itemId).getComponentId();
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
