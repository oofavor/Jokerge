package ru.ofavor.tabs.store;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.ofavor.auth.AuthState;

import java.sql.SQLException;

public class StoreController {
    StoreList list = new StoreList();

    @FXML TableView<Store> table;

    @FXML TableColumn<Store, String> faxCol;
    @FXML TableColumn<Store, String> addressCol;

    @FXML TextField faxField;
    @FXML TextField addressField;

    @FXML
    public void initialize() {
        faxCol.setCellValueFactory(new PropertyValueFactory<Store, String>("fax"));
        addressCol.setCellValueFactory(new PropertyValueFactory<Store, String>("address"));
    }

    @FXML
    private void fetch() throws SQLException {
        list.fetch();
        table.setItems(list.getItems());
    }

    @FXML
    private void add() throws SQLException {
        String fax = "";
        if (AuthState.getInstance().isFactory()) {
            fax = faxField.getText();
            if (fax.length()==0 && AuthState.getInstance().isStore()) {
                fax = AuthState.getInstance().getFax();
            }
        } else if (AuthState.getInstance().isStore()) {
            fax = AuthState.getInstance().getFax();
        } else {
            return;
        }

        String address = addressField.getText();

        list.add(new Store(fax, address));
    }

    @FXML
    private void delete() throws SQLException {
        TableView.TableViewSelectionModel<Store> selected = table.getSelectionModel();

        if (!AuthState.getInstance().isFactory()) {}
        else if (!AuthState.getInstance().isStore() ||
                !AuthState.getInstance().getFax().equals(selected.getSelectedItem().getFax())) return;
        else { return;}

        int deleteIdx = selected.getFocusedIndex();
        list.delete(deleteIdx);
    }

    @FXML
    private void update() throws SQLException {
        String fax = faxField.getText();
        if (AuthState.getInstance().isFactory()) {
        } else if (AuthState.getInstance().isStore()) {
            if (!AuthState.getInstance().getFax().equals(table.getSelectionModel().getSelectedItem().getFax())) {
                return;
            }
            fax = AuthState.getInstance().getFax();
        } else {
            return;
        }
        String address = addressField.getText();

        if (fax.length() == 0) fax = null;
        if (address.length() == 0) address = null;

        TableView.TableViewSelectionModel<Store> selected = table.getSelectionModel();
        int deleteIdx = selected.getFocusedIndex();

        list.update(deleteIdx, fax, address);
    }
}
