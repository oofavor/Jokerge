package ru.ofavor.tabs.store;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.ofavor.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StoreList {
    private ObservableList<Store> items = FXCollections.observableArrayList();

    public ObservableList<Store> getItems() {
        return items;
    }

    public void fetch() throws SQLException {
        items.clear();
        String query = "SELECT id, price, type, line FROM Furnitures";
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
            String line = result.getString("line");

            Store newStore = new Store(article, price, type, line);

            items.add(newStore);
        }
    }

    public void add(Store store) throws SQLException {
        String query = "INSERT INTO Furnitures (id, price, type, line) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(query);

        statement.setInt(1, store.getArticle());
        statement.setInt(2, store.getPrice());
        statement.setString(3, store.getType());
        statement.setString(4, store.getLine());

        try {
            statement.executeUpdate();
            items.add(store);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int itemId) throws SQLException {
        int article = items.get(itemId).getArticle();
        String query = "DELETE FROM Furnitures WHERE id = ?";
        PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(query);

        statement.setInt(1, article);

        try {
            statement.executeUpdate();
            items.remove(itemId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(int itemId, Integer article, Integer price, String type, String line) throws SQLException {
        int articleID = items.get(itemId).getArticle();
        int i = 0;
        System.out.println(price);
        System.out.println(article);

        String setString = "SET ";
        if (article != null) setString += "id=?,";
        if (price != null) setString += "price=?,";
        if (type != null) setString += "type=?,";
        if (line != null) setString += "line=?,";

        setString = setString.replaceAll(",$", "\n");

        String query = "UPDATE Furnitures " + setString + "WHERE id=?;";
        System.out.println(query);

        PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(query);

        if (article != null) statement.setInt(++i, article);
        if (price != null) statement.setInt(++i, price);
        if (type != null) statement.setString(++i, type);
        if (line != null) statement.setString(++i, line);

        statement.setInt(++i, articleID);

        try {
            statement.executeUpdate();
            fetch();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
