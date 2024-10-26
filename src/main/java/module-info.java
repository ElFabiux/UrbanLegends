module com.mycompany.urbanlegends {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    

    opens com.mycompany.urbanlegends to javafx.fxml;
    exports com.mycompany.urbanlegends;
    
    opens controllers to javafx.fxml;
    exports controllers;
    
    
//     opens characters to javafx.fxml;
//     exports characters;
     
//     opens characters.factory to javafx.fxml;
//     exports characters.factory;
//     
//  
    
    
    
    
    
   
}
