package ru.ofavor.tabs.component;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.ofavor.auth.AuthState;

import java.sql.SQLException;

public class ComponentController {
    ComponentList list = new ComponentList();

    @FXML TableView<Component> table;

    @FXML TableColumn<Component, Integer> articleCol;
    @FXML TableColumn<Component, String> typeCol;
    @FXML TableColumn<Component, Integer> priceCol;

    @FXML TextField articleField;
    @FXML TextField typeField;
    @FXML TextField priceField;

    @FXML
    public void initialize() {
        articleCol.setCellValueFactory(new PropertyValueFactory<Component, Integer>("article"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Component, String>("type"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Component, Integer>("price"));
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

        list.add(new Component(article, price, type));
    }

    @FXML
    private void delete() throws SQLException {
//        if (!AuthState.getInstance().isFactory()) return;

        TableView.TableViewSelectionModel<Component> selected = table.getSelectionModel();
        int deleteIdx = selected.getFocusedIndex();
        list.delete(deleteIdx);
    }

    @FXML
    private void update() throws SQLException {
//        if (!AuthState.getInstance().isFactory()) return;

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

        if (type.length() == 0) type = null;

        TableView.TableViewSelectionModel<Component> selected = table.getSelectionModel();
        int deleteIdx = selected.getFocusedIndex();

        list.update(deleteIdx, article, price, type);
    }
}
