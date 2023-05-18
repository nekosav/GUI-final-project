package com.example.gui_final_project;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Random;

public class Playground_controller  {

    @FXML
    private ImageView picked_res; //картинка выбранного ресурса
    @FXML
    private ImageView waves_fr1, waves_fr2,waves_fr3;//кадры воды для фона
    @FXML
    private GridPane playground;//поле 8*8
    @FXML
    private Pane background_pane;//основная Pane
    @FXML
    private Button block0, block1, block2;
    @FXML
    private Label label1,label2;



    private int[] b_res_num ={0,0,0}; //номер ресурса в кнопке

    private int[][] selected_tiles_cords = {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
    private int time = 0, counter =0, picked_res_num = -1,b_picked_num = -1; //счётчики и вспомогательные переменные

    private final Font  customFont = Font.loadFont("file:src/main/resources/com/example/gui_final_project/font/big-shot.ttf",24);

    private final Image selected_tile_image = new Image(new FileInputStream("src/main/resources/com/example/gui_final_project/textures/selected_tile.png"));



    private Resources resources = new Resources();
    Random random = new Random();




    public Playground_controller() throws FileNotFoundException {
    }

    @FXML
    public void initialize() throws FileNotFoundException {

        if (!(customFont.equals(null) )){
            label1.setFont(customFont);
            label2.setFont(customFont);
        }
        background_pane.setOnMouseClicked(this::unselect_all_tiles);

        Set_buttons_params();
        Draw_playground();
        Set_background();

    }

    @FXML
    public void click_button(MouseEvent mouseEvent){ //выбор ресурса
        Button button = (Button) mouseEvent.getSource();
        if (button == block0) {
            picked_res.setImage(resources.getResources_options()[b_res_num[0]]);
            picked_res_num = b_res_num[0];
            b_picked_num = 0;
        }else if (button == block1){
            picked_res.setImage(resources.getResources_options()[b_res_num[1]]);
            picked_res_num = b_res_num[1];
            b_picked_num = 1;
        }else if (button== block2){
            picked_res.setImage(resources.getResources_options()[b_res_num[2]]);
            picked_res_num = b_res_num[2];
            b_picked_num = 2;
        }
    }

    @FXML
    public void click_square(MouseEvent mouseEvent){


    }


    public void place_resource(MouseEvent mouseEvent){ //расстановка ресурсов на поле
        Pane cell_pane = (Pane) mouseEvent.getSource(); // метод возращающий выбранный тип объекта по позиции клика
        ImageView resource_cube = (ImageView) cell_pane.getChildren().get(0);
        ImageView selected_tile = (ImageView) cell_pane.getChildren().get(1);
        javafx.scene.Node clickedNode = (javafx.scene.Node) mouseEvent.getSource();
        int row = GridPane.getRowIndex(clickedNode);
        int column = GridPane.getColumnIndex(clickedNode);

        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
            if ((picked_res_num > -1) & (resources.getRes_cords()[row][column] == -1)) { //проверяем выбран ли ресурс и свободна ли клетка
                resource_cube.setImage(resources.getResources_tiles_options()[picked_res_num]);
                resources.setRes_cords(row, column, picked_res_num); // добавляем ресурс в карту ресурсов
                System.out.println("Вы поставили ресурс:" + resources.getRes_cords()[row][column]);
                b_res_num[b_picked_num] = resources.getFirstResource();
                switch (b_picked_num) {
                    case 0:
                        block0.graphicProperty().setValue(new ImageView(resources.getResources_options()[b_res_num[0]]));
                    case 1:
                        block1.graphicProperty().setValue(new ImageView(resources.getResources_options()[b_res_num[1]]));
                    case 2:
                        block2.graphicProperty().setValue(new ImageView(resources.getResources_options()[b_res_num[2]]));
                }
                picked_res.setImage(resources.getResources_options()[b_res_num[b_picked_num]]);

                picked_res_num = b_res_num[b_picked_num];


            }
            resource_cube.setVisible(true);
        }
        else if (mouseEvent.getButton() == MouseButton.SECONDARY){
            selected_tiles_cords[row][column] = 1;
            selected_tile.setVisible(true);
        }
    }

    private void Draw_playground(){
        //расставляем по карте пустые ImageView, куда дальше будем вставлять картинки ресурсов
        for (int row =0; row<4; row ++){
            for (int col=0; col<4; col++){
                Pane cell_pane = new Pane();

                ImageView resource_cube = new ImageView();
                resource_cube.setFitHeight(100);
                resource_cube.setFitWidth(100);
                cell_pane.getChildren().add(resource_cube);
                resource_cube.setVisible(false);

                ImageView selected_tile = new ImageView(selected_tile_image);
                selected_tile.setFitHeight(100);
                selected_tile.setFitWidth(100);
                cell_pane.getChildren().add(selected_tile);
                selected_tile.setVisible(false);


                cell_pane.setOnMouseClicked(this::place_resource);
                playground.add(cell_pane,row,col);
            }
        }
    }
    private void Set_buttons_params(){
        //выбор начальных 3-х ресурсов
        b_res_num[0] = resources.getFirstResource();
        b_res_num[1] = resources.getFirstResource();
        b_res_num[2] = resources.getFirstResource();
        block0.graphicProperty().setValue(new ImageView(resources.getResources_options()[b_res_num[0]]));
        block1.graphicProperty().setValue(new ImageView(resources.getResources_options()[b_res_num[1]]));
        block2.graphicProperty().setValue(new ImageView(resources.getResources_options()[b_res_num[2]]));
        b_picked_num= 0;
        picked_res_num = b_res_num[0];
        picked_res.setImage(resources.getResources_options()[b_res_num[0]]);
    }

    private void Set_background(){
        //тут я попробовал сделать фон в виде море, но чёт хз
        //скорее всего переделаю
        LinkedList<ImageView> waves = new LinkedList<>();
        waves.add(waves_fr1);
        waves.add(waves_fr2);
        waves.add(waves_fr3);

        waves.add(waves_fr2);

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                time+=1;

                if (time % 60 ==0){
                    waves.get(counter).setVisible(false);
                    counter = (counter +1) % 4;
                    waves.get(counter).setVisible(true);
                    //System.out.println(counter);
                }


            }
        };
        animationTimer.start();
    }

    private Pane getPaneByCell(int row, int col) {
        for (int i = 16; i < playground.getChildren().size(); i++) {
            if (GridPane.getRowIndex(playground.getChildren().get(i)) == row
                    && GridPane.getColumnIndex(playground.getChildren().get(i)) == col) {
                return (Pane) playground.getChildren().get(i);
            }
        }
        return null;
    }

    private void unselect_all_tiles(MouseEvent mouseEvent){
        if (!((mouseEvent.getX() >= playground.getLayoutX() ) &(mouseEvent.getX() <= playground.getLayoutX() +playground.getWidth()) &
                (mouseEvent.getY() >= playground.getLayoutY() ) &(mouseEvent.getY() <= playground.getLayoutY() +playground.getHeight()))){

               for (int i = 0; i < 4; i++) {
                   for (int j = 0; j < 4; j++) {
                       selected_tiles_cords[i][j] = 0;
                       Pane cell_pane = getPaneByCell(i, j);
                       ImageView selected_tile = (ImageView) cell_pane.getChildren().get(1);
                       selected_tile.setVisible(false);
                   }
               }

        }
        System.out.println("Координаты мыши: х = "+ mouseEvent.getX()+"; y = "+mouseEvent.getY());
        System.out.println("Координаты левой вершины: х = "+ playground.getLayoutX()+"; y = "+playground.getLayoutY());
        System.out.println("Координаты правой вершины: х = "+ playground.getLayoutX()+playground.getWidth()+"; y = "+playground.getLayoutY()+playground.getHeight());

    }


}