module com.example.myispsux {
    requires javafx.controls;
    requires javafx.fxml;
        requires javafx.web;
            
        requires org.controlsfx.controls;
                    requires org.kordamp.ikonli.javafx;
                requires eu.hansolo.tilesfx;
        
    opens com.example.myispsux to javafx.fxml;
    exports com.example.myispsux;
}