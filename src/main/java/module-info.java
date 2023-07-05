module ru.ofavor {
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;

    opens ru.ofavor to javafx.fxml;
    opens ru.ofavor.auth to javafx.fxml;
    opens ru.ofavor.tabs to javafx.fxml;
    opens ru.ofavor.tabs.furniture to javafx.fxml;

    exports ru.ofavor;
    exports ru.ofavor.auth;
    exports ru.ofavor.tabs;
    exports ru.ofavor.tabs.furniture;
}
