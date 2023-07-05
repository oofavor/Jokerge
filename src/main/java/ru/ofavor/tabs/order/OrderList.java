package ru.ofavor.tabs.order;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.ofavor.Database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderList {
    private ObservableList<Order> items = FXCollections.observableArrayList();

    public ObservableList<Order> getItems() {
        return items;
    }

    public void fetch() throws SQLException {
        items.clear();
        String query = "SELECT id, date, storeFax FROM Orders";
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
            String fax = result.getString("storeFax");
            Date date = result.getDate("date");

            Order newOrder = new Order(article, fax, date);

            items.add(newOrder);
        }
    }

    public void add(Order order) throws SQLException {
        String query = "INSERT INTO Orders (id, storeFax, date) VALUES (?, ?, ?)";
        PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(query);

        statement.setInt(1, order.getArticle());
        statement.setString(2, order.getStoreFax());
        statement.setDate(3, order.getDate());

        try {
            statement.executeUpdate();
            items.add(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int itemId) throws SQLException {
        int article = items.get(itemId).getArticle();
        String query = "DELETE FROM Orders WHERE id = ?";
        PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(query);

        statement.setInt(1, article);

        try {
            statement.executeUpdate();
            items.remove(itemId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(int itemId, Integer article, String storeFax) throws SQLException {
        int articleID = items.get(itemId).getArticle();
        int i = 0;

        String setString = "SET ";
        if (article != null) setString += "id=?,";
        if (storeFax != null) setString += "storeFax=?,";

        setString = setString.replaceAll(",$", "\n");

        String query = "UPDATE Orders " + setString + "WHERE id=?;";

        PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(query);

        if (article != null) statement.setInt(++i, article);
        if (storeFax != null) statement.setString(++i, storeFax);

        statement.setInt(++i, articleID);

        try {
            statement.executeUpdate();
            fetch();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
