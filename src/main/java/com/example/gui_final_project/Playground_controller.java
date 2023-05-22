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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
    @FXML
    private ImageView bank_view, ambar_view, cottage_view, shelter_view, fountain_view, shop_view, temple_view;


    private ArrayList<ImageView> building_pickers = new ArrayList<>();
    private ArrayList<NewBuilding> buildings = new ArrayList<>();

    private int[] b_res_num ={0,0,0}; //номер ресурса в кнопке

    //private int[][] selected_tiles_cords = {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
    private ArrayList<Cell> selected_tiles_cord = new ArrayList<>();
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
        background_pane.setOnMouseClicked(this::unselect_all_tiles_pane);

        Set_buttons_params();
        Draw_playground();
        Set_background();
        building_pickers.addAll(List.of(ambar_view, bank_view, cottage_view, fountain_view, shelter_view, shop_view, temple_view));
        buildings.addAll(List.of(new Ambar(),new Bank(), new Cottage(), new Fountain(), new Shelter(), new Shop(), new Temple()));
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
    public void click_building_picker(MouseEvent mouseEvent){
        ImageView res_picker = (ImageView) mouseEvent.getSource();

        for (ImageView image : building_pickers){

            if (res_picker == image){
                int building_num = building_pickers.indexOf(image);
                if (buildings.get(building_num).compareGraph(selected_tiles_cord)) {
                    System.out.println("Ебать дом, ахуеть");
                    Pane cell_pane = getPaneByCell(selected_tiles_cord.get(0).x, selected_tiles_cord.get(0).y );
                    ImageView resource_cube = (ImageView) cell_pane.getChildren().get(0);
                    resource_cube.setImage(buildings.get(building_num).getBuilding_image());
                    resources.getRes_cords()[selected_tiles_cord.get(0).x][selected_tiles_cord.get(0).y] = buildings.get(building_num).getRes_id();
                    ImageView selected_res_cub = (ImageView) cell_pane.getChildren().get(1);
                    selected_res_cub.setVisible(false);
                    selected_tiles_cord.remove(0);
                    for (int i=0; i<selected_tiles_cord.size(); i++){
                        Pane temp_pane = getPaneByCell(selected_tiles_cord.get(i).x,selected_tiles_cord.get(i).y);
                        ImageView temp_res = (ImageView) temp_pane.getChildren().get(0);
                        temp_res.setVisible(false);
                        resources.getRes_cords()[selected_tiles_cord.get(i).x][selected_tiles_cord.get(i).y] = -1;
                    }

                }
            }

        }

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
            unselect();


        }
        else if (mouseEvent.getButton() == MouseButton.SECONDARY){
            Cell cell = new Cell(resources.getRes_cords()[row][column], row, column);
            boolean flag = true;
            for (Cell temp_cell : selected_tiles_cord){
                if ((cell.x ==temp_cell.x)&(cell.y == temp_cell.y)){
                    flag = false;
                }
            }
            if (flag) {
                selected_tiles_cord.add(cell);
                selected_tile.setVisible(true);
            }
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

    private void unselect_all_tiles_pane(MouseEvent mouseEvent){
        if (!((mouseEvent.getX() >= playground.getLayoutX() ) &(mouseEvent.getX() <= playground.getLayoutX() +playground.getWidth()) &
                (mouseEvent.getY() >= playground.getLayoutY() ) &(mouseEvent.getY() <= playground.getLayoutY() +playground.getHeight())
        & (mouseEvent.getX()>800))){

            unselect();
        }


    }
    public void unselect(){
            for (Cell cell : selected_tiles_cord) {

                Pane cell_pane_1 = getPaneByCell(cell.x, cell.y);
                ImageView selected_tile_1 = (ImageView) cell_pane_1.getChildren().get(1);
                selected_tile_1.setVisible(false);

            }
            while (selected_tiles_cord.size()>0){
                selected_tiles_cord.remove(0);
            }
    }


}