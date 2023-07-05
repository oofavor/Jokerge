package ru.ofavor.tabs.furniture;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.ofavor.auth.AuthState;

import java.sql.SQLException;

public class FurnitureController {
    FurnitureList list = new FurnitureList();

    @FXML TableView<Furniture> table;

    @FXML TableColumn<Furniture, Integer> articleCol;
    @FXML TableColumn<Furniture, String> typeCol;
    @FXML TableColumn<Furniture, Integer> priceCol;
    @FXML TableColumn<Furniture, String> lineCol;

    @FXML TextField articleField;
    @FXML TextField typeField;
    @FXML TextField priceField;
    @FXML TextField lineField;

    @FXML
    public void initialize() {
        articleCol.setCellValueFactory(new PropertyValueFactory<Furniture, Integer>("article"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Furniture, String>("type"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Furniture, Integer>("price"));
        lineCol.setCellValueFactory(new PropertyValueFactory<Furniture, String>("line"));
    }

    @FXML
    private void fetch() throws SQLException {
        list.fetch();
        table.setItems(list.getItems());
    }

    @FXML
    private void add() throws SQLException {
        if (!AuthState.getInstance().isFactory()) return;

        int article = 0;
        int price = 0;

        try {
            article = Integer.parseInt(articleField.getText());
            price = Integer.parseInt(priceField.getText());
        } catch (Exception e) {
            return;
        }

        String type = typeField.getText();
        String line = lineField.getText();

        list.add(new Furniture(article, price, type, line));
    }

    @FXML
    private void delete() throws SQLException {
        if (!AuthState.getInstance().isFactory()) return;

        TableView.TableViewSelectionModel<Furniture> selected = table.getSelectionModel();
        int deleteIdx = selected.getFocusedIndex();
        list.delete(deleteIdx);
    }

    @FXML
    private void update() throws SQLException {
        if (!AuthState.getInstance().isFactory()) return;

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

        TableView.TableViewSelectionModel<Furniture> selected = table.getSelectionModel();
        int deleteIdx = selected.getFocusedIndex();

        list.update(deleteIdx, article, price, type, line);
    }
}
