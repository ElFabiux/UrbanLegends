module com.mycompany.urbanlegends {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.urbanlegends to javafx.fxml;
    exports com.mycompany.urbanlegends;
}
