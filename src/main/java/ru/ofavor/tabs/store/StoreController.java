package ru.ofavor.tabs.store;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class StoreController {
    StoreList list = new StoreList();

    @FXML TableView<Store> table;

    @FXML TableColumn<Store, Integer> articleCol;
    @FXML TableColumn<Store, String> typeCol;
    @FXML TableColumn<Store, Integer> priceCol;
    @FXML TableColumn<Store, String> lineCol;

    @FXML TextField articleField;
    @FXML TextField typeField;
    @FXML TextField priceField;
    @FXML TextField lineField;

    @FXML
    public void initialize() {
        articleCol.setCellValueFactory(new PropertyValueFactory<Store, Integer>("article"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Store, String>("type"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Store, Integer>("price"));
        lineCol.setCellValueFactory(new PropertyValueFactory<Store, String>("line"));
    }

    @FXML
    private void fetch() throws SQLException {
        list.fetch();
        table.setItems(list.getItems());
    }

    @FXML
    private void add() throws SQLException {
//        if (!AuthState.getInstance().isFactory()) return;

        int article = 0;
        int price = 0;

        try {
            article = Integer.parseInt(articleField.getText());
            price = Integer.parseInt(priceField.getText());
        } catch (Exception e) {}

        String type = typeField.getText();
        String line = lineField.getText();

        list.add(new Store(article, price, type, line));
    }

    @FXML
    private void delete() throws SQLException {
        TableView.TableViewSelectionModel<Store> selected = table.getSelectionModel();
        int deleteIdx = selected.getFocusedIndex();
        list.delete(deleteIdx);
    }

    @FXML
    private void update() throws SQLException {
        Integer article;
        Integer price;

        try {
            price = Integer.valueOf(priceField.getText());
        } catch (Exception e) {
            price = null;
        }

        try {
            article = Integer.valueOf(articleField.getText());
        } catch (Exception e) {
            article = null;
        }

        String type = typeField.getText();
        String line = lineField.getText();

        if (type.length() == 0) type = null;
        if (line.length() == 0) line = null;

        TableView.TableViewSelectionModel<Store> selected = table.getSelectionModel();
        int deleteIdx = selected.getFocusedIndex();

        list.update(deleteIdx, article, price, type, line);
    }
}
