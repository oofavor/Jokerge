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
        String query = "SELECT fax, address FROM Stores";
        PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(query);

        ResultSet result;
        try {
            result = statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        while (result.next()) {
            String fax = result.getString("fax");
            String address = result.getString("address");

            Store newStore = new Store(fax, address);

            items.add(newStore);
        }
    }

    public void add(Store store) throws SQLException {
        String query = "INSERT INTO Stores (fax, address) VALUES (?, ?)";
        PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(query);

        statement.setString(1, store.getFax());
        statement.setString(2, store.getAddress());

        try {
            statement.executeUpdate();
            items.add(store);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int itemId) throws SQLException {
        String fax = items.get(itemId).getFax();
        String query = "DELETE FROM Stores WHERE fax = ?";
        PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(query);

        statement.setString(1, fax);

        try {
            statement.executeUpdate();
            items.remove(itemId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(int itemId, String fax, String address) throws SQLException {
        String faxId = items.get(itemId).getFax();
        int i = 0;

        String setString = "SET ";
        if (fax != null) setString += "fax=?,";
        if (address != null) setString += "address=?,";

        setString = setString.replaceAll(",$", "\n");

        String query = "UPDATE Stores " + setString + "WHERE fax=?;";

        PreparedStatement statement = Database.getInstance().getConnection().prepareStatement(query);

        if (fax != null) statement.setString(++i, fax);
        if (address != null) statement.setString(++i, address);

        statement.setString(++i, faxId);

        try {
            statement.executeUpdate();
            fetch();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
