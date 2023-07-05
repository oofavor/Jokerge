package ru.ofavor.tabs.furniturecomponent;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.ofavor.auth.AuthState;

import java.sql.SQLException;

public class FurnitureComponentController {
    FurnitureComponentList list = new FurnitureComponentList();

    @FXML TableView<FurnitureComponent> table;

    @FXML TableColumn<FurnitureComponent, Integer> furnitureCol;
    @FXML TableColumn<FurnitureComponent, Integer> componentCol;
    @FXML TableColumn<FurnitureComponent, Integer> quantityCol;

    @FXML TextField furnitureField;
    @FXML TextField componentField;
    @FXML TextField quantityField;

    @FXML
    public void initialize() {
        furnitureCol.setCellValueFactory(new PropertyValueFactory<FurnitureComponent, Integer>("furnitureId"));
        componentCol.setCellValueFactory(new PropertyValueFactory<FurnitureComponent, Integer>("componentId"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<FurnitureComponent, Integer>("quantity"));
    }

    @FXML
    private void fetch() throws SQLException {
        list.fetch();
        table.setItems(list.getItems());
    }

    @FXML
    private void add() throws SQLException {
        if (!AuthState.getInstance().isFactory()) return;

        int furnitureId = 0;
        int componentId = 0;
        int quantity = 0;

        try {
            furnitureId = Integer.parseInt(furnitureField.getText());
            componentId = Integer.parseInt(componentField.getText());
            quantity =    Integer.parseInt(quantityField.getText());
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        list.add(new FurnitureComponent(furnitureId, componentId, quantity));
    }

    @FXML
    private void delete() throws SQLException {
        if (!AuthState.getInstance().isFactory()) return;

        TableView.TableViewSelectionModel<FurnitureComponent> selected = table.getSelectionModel();
        int deleteIdx = selected.getFocusedIndex();
        list.delete(deleteIdx);
    }

    @FXML
    private void update() throws SQLException {
        if (!AuthState.getInstance().isFactory()) return;

        Integer furnitureId;
        Integer componentId;
        Integer quantity;

        try {
            furnitureId = Integer.valueOf(furnitureField.getText());
        } catch (Exception e) {
            furnitureId = null;
        }

        try {
            componentId = Integer.valueOf(componentField.getText());
        } catch (Exception e) {
            componentId = null;
        }

        try {
            quantity = Integer.valueOf(quantityField.getText());
        } catch (Exception e) {
            quantity = null;
        }

        TableView.TableViewSelectionModel<FurnitureComponent> selected = table.getSelectionModel();
        int deleteIdx = selected.getFocusedIndex();

        list.update(deleteIdx, furnitureId, componentId, quantity);
    }
}
