package ru.ofavor.tabs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class TableController {
    @FXML
    TableView<TableModel> table;

    @FXML
    TableColumn<TableModel, String> col1;

    @FXML
    TableColumn<TableModel, String> col2;

    ObservableList<TableModel> items;

    @FXML
    private void fetch() {
        items = FXCollections.observableArrayList(
                new TableModel("Tom", "1232"),
                new TableModel("Bob", "12312"),
                new TableModel("Sam", "123"),
                new TableModel("Alice", "1w3"),
                new TableModel("Bob", "12312"),
                new TableModel("Sam", "123"),
                new TableModel("Bob", "12312"),
                new TableModel("Sam", "123"),
                new TableModel("Bob", "12312"),
                new TableModel("Sam", "123"),
                new TableModel("Sam", "123"),
                new TableModel("Bob", "12312"),
                new TableModel("Sam", "123"),
                new TableModel("Bob", "12312"),
                new TableModel("Sam", "123"),
                new TableModel("Tom", "1232"),
                new TableModel("Bob", "12312"),
                new TableModel("Sam", "123"),
                new TableModel("Alice", "1w3"),
                new TableModel("Bob", "12312"),
                new TableModel("Sam", "123"),
                new TableModel("Bob", "12312"),
                new TableModel("Sam", "123"),
                new TableModel("Bob", "12312"),
                new TableModel("Sam", "123"),
                new TableModel("Sam", "123"),
                new TableModel("Bob", "12312"),
                new TableModel("Sam", "123"),
                new TableModel("Bob", "12312"),
                new TableModel("Sam", "123")
            );

        col1.setCellValueFactory(new PropertyValueFactory<TableModel, String>("a"));
        col2.setCellValueFactory(new PropertyValueFactory<TableModel, String>("b"));

        table.setItems(items);
    }

    @FXML
    private void delete() {
        TableView.TableViewSelectionModel<TableModel> selected = table.getSelectionModel();
        int deleteIdx = selected.getFocusedIndex();
        TableModel model = items.get(deleteIdx);
        items.remove(deleteIdx);
        table.setItems(items);
    }

}
