package ru.ofavor.tabs.component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.ofavor.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ComponentList {
    private ObservableList<Component> items = FXCollections.observableArrayList();

    public ObservableList<Component> getItems() {
        return items;
    }

    public void fetch() throws SQLException {
        items.clear();
        String query = "SELECT id, price, type FROM Components";
        PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(query);

        ResultSet result;
        try {
            result = statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        while (result.next()) {
            int article = result.getInt("id");
            int price = result.getInt("price");
            String type = result.getString("type");

            Component newComponent = new Component(article, price, type);

            items.add(newComponent);
        }
    }

    public void add(Component component) throws SQLException {
        String query = "INSERT INTO Components (id, price, type) VALUES (?, ?, ?)";
        PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(query);

        statement.setInt(1, component.getArticle());
        statement.setInt(2, component.getPrice());
        statement.setString(3, component.getType());

        try {
            statement.executeUpdate();
            items.add(component);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int itemId) throws SQLException {
        int article = items.get(itemId).getArticle();
        String query = "DELETE FROM Components WHERE id = ?";
        PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(query);

        statement.setInt(1, article);

        try {
            statement.executeUpdate();
            items.remove(itemId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(int itemId, Integer article, Integer price, String type) throws SQLException {
        int articleID = items.get(itemId).getArticle();
        int i = 0;

        String setString = "SET ";
        if (article != null) setString += "id=?,";
        if (price != null) setString += "price=?,";
        if (type != null) setString += "type=?,";

        setString = setString.replaceAll(",$", "\n");

        String query = "UPDATE Components " + setString + "WHERE id=?;";
        System.out.println(query);

        PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(query);

        if (article != null) statement.setInt(++i, article);
        if (price != null) statement.setInt(++i, price);
        if (type != null) statement.setString(++i, type);

        statement.setInt(++i, articleID);

        try {
            statement.executeUpdate();
            fetch();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
