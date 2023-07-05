module ru.ofavor {
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;

    opens ru.ofavor to javafx.fxml;
    opens ru.ofavor.auth to javafx.fxml;
    opens ru.ofavor.tabs to javafx.fxml;
    opens ru.ofavor.tabs.furniture to javafx.fxml;
    opens ru.ofavor.tabs.store to javafx.fxml;
    opens ru.ofavor.tabs.component to javafx.fxml;
    opens ru.ofavor.tabs.order to javafx.fxml;

    exports ru.ofavor;
    exports ru.ofavor.auth;
    exports ru.ofavor.tabs;
    exports ru.ofavor.tabs.furniture;
    exports ru.ofavor.tabs.store;
    exports ru.ofavor.tabs.component;
    exports ru.ofavor.tabs.order;
}
