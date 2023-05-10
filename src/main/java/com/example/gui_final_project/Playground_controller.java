package com.example.gui_final_project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Playground_controller  {
    @FXML
    private Button block1, block2, block3;

    @FXML
    private ColorPicker color_picker;

    @FXML
    private GridPane playground;

    @FXML
    public void initialize(){
        for (int row =0; row<4; row ++){
            for (int col=0; col<4; col++){
                Pane cell_pane = new Pane();

                Rectangle resource_cube = new Rectangle(50,50);
                resource_cube.setFill(Color.BLUE);
                cell_pane.getChildren().add(resource_cube);

                resource_cube.setLayoutY(25);
                resource_cube.setLayoutX(25);
                resource_cube.setVisible(false);

                cell_pane.setOnMouseClicked(this::new_method);
                playground.add(cell_pane,row,col);


            }
        }
    }

    @FXML
    public void click_button(MouseEvent mouseEvent){

    }

    @FXML
    public void click_square(MouseEvent mouseEvent){


    }


    public void new_method(MouseEvent mouseEvent){
        Pane cell_pane = (Pane) mouseEvent.getSource();
        System.out.println(mouseEvent.getSource());
        Rectangle resource_cube = (Rectangle) cell_pane.getChildren().get(0);
        resource_cube.setVisible(true);
    }




}