package ru.ofavor.tabs.orderfurniture;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.ofavor.auth.AuthState;

import java.sql.SQLException;

public class OrderFurnitureController {
    OrderFurnitureList list = new OrderFurnitureList();

    @FXML TableView<OrderFurniture> table;

    @FXML TableColumn<OrderFurniture, Integer> furnitureCol;
    @FXML TableColumn<OrderFurniture, Integer> orderCol;
    @FXML TableColumn<OrderFurniture, Integer> quantityCol;

    @FXML TextField furnitureField;
    @FXML TextField orderField;
    @FXML TextField quantityField;

    @FXML
    public void initialize() {
        furnitureCol.setCellValueFactory(new PropertyValueFactory<OrderFurniture, Integer>("furnitureId"));
        orderCol.setCellValueFactory(new PropertyValueFactory<OrderFurniture, Integer>("orderId"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<OrderFurniture, Integer>("quantity"));
    }

    @FXML
    private void fetch() throws SQLException {
        list.fetch();
        table.setItems(list.getItems());
    }

    @FXML
    private void add() throws SQLException {
//        if (!AuthState.getInstance().isFactory()) return;

        int furnitureId = 0;
        int orderId = 0;
        int quantity = 0;

        try {
            furnitureId = Integer.parseInt(furnitureField.getText());
            orderId = Integer.parseInt(orderField.getText());
            quantity =    Integer.parseInt(quantityField.getText());
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        list.add(new OrderFurniture(furnitureId, orderId, quantity));
    }

    @FXML
    private void delete() throws SQLException {
//        if (!AuthState.getInstance().isFactory()) return;

        TableView.TableViewSelectionModel<OrderFurniture> selected = table.getSelectionModel();
        int deleteIdx = selected.getFocusedIndex();
        list.delete(deleteIdx);
    }

    @FXML
    private void update() throws SQLException {
//        if (!AuthState.getInstance().isFactory()) return;

        Integer furnitureId;
        Integer orderId;
        Integer quantity;

        try {
            furnitureId = Integer.valueOf(furnitureField.getText());
        } catch (Exception e) {
            furnitureId = null;
        }

        try {
            orderId = Integer.valueOf(orderField.getText());
        } catch (Exception e) {
            orderId = null;
        }

        try {
            quantity = Integer.valueOf(quantityField.getText());
        } catch (Exception e) {
            quantity = null;
        }

        TableView.TableViewSelectionModel<OrderFurniture> selected = table.getSelectionModel();
        int deleteIdx = selected.getFocusedIndex();

        list.update(deleteIdx, furnitureId, orderId, quantity);
    }
}
