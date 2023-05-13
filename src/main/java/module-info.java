module com.example.gui_final_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jgrapht.core;


    opens com.example.gui_final_project to javafx.fxml;
    exports com.example.gui_final_project;
}