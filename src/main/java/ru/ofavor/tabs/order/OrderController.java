package ru.ofavor.tabs.order;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.ofavor.auth.AuthState;
import ru.ofavor.auth.AuthUtils;

import java.sql.SQLException;
import java.util.Date;

public class OrderController {
    OrderList list = new OrderList();

    @FXML TableView<Order> table;

    @FXML TableColumn<Order, Integer> articleCol;
    @FXML TableColumn<Order, String> faxCol;
    @FXML TableColumn<Order, Date> dateCol;

    @FXML TextField articleField;
    @FXML TextField faxField;

    @FXML
    public void initialize() {
        articleCol.setCellValueFactory(new PropertyValueFactory<Order, Integer>("article"));
        faxCol.setCellValueFactory(new PropertyValueFactory<Order, String>("storeFax"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Order, Date>("date"));
    }

    @FXML
    private void fetch() throws SQLException {
        list.fetch();
        table.setItems(list.getItems());
    }

    @FXML
    private void add() throws SQLException {
        if (!AuthState.getInstance().isStore()) return;

        int article=0;

        try {
            article = Integer.parseInt(articleField.getText());
        } catch (Exception e) {
            return;
        }

        String fax = AuthState.getInstance().getFax();

        list.add(new Order(article, fax, new java.sql.Date(new Date().getTime())));
    }

    @FXML
    private void delete() throws SQLException {
        if (!AuthState.getInstance().isFactory()) return;

        TableView.TableViewSelectionModel<Order> selected = table.getSelectionModel();
        int deleteIdx = selected.getFocusedIndex();
        list.delete(deleteIdx);
    }

    @FXML
    private void update() throws SQLException {
        if (!AuthState.getInstance().isFactory()) return;

        Integer article;

        try {
            article = Integer.valueOf(articleField.getText());
        } catch (Exception e) {
            article = null;
        }

        String fax = faxField.getText();

        if (fax.length() == 0) fax = null;

        TableView.TableViewSelectionModel<Order> selected = table.getSelectionModel();
        int deleteIdx = selected.getFocusedIndex();

        list.update(deleteIdx, article, fax);
    }
}
