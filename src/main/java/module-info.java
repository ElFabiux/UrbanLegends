module com.mycompany.urbanlegends {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    

    opens com.mycompany.urbanlegends to javafx.fxml;
    exports com.mycompany.urbanlegends;
    
    opens controllers to javafx.fxml;
    exports controllers;
    
    
     opens playableCharacters to javafx.fxml;
     exports playableCharacters;


   
}
