package com.example.gui_final_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.util.ArrayList;



public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Playground1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);


        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");



        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch();

    }
}