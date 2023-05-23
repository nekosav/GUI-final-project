package com.example.gui_final_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Playground1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        //TODO Спиздить у Севы нормальный полноэкранный режим
//        stage.setMaxHeight(1080);
//        stage.setMaxWidth(1920);
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");



        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
//        Building building = new Building();
//        ArrayList<Cell> cords= new ArrayList<>();
//        cords.add(new Cell(1,0,0));
//        cords.add(new Cell(2,1,0));
//        cords.add(new Cell(1,0,1));
//        cords.add(new Cell(0,1,1));
//        System.out.println(building.Graph_Compare(cords));
//        System.exit(10);
    }
}