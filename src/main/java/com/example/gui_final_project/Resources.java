package com.example.gui_final_project;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

public class Resources {

    private Image brown_cube = new Image(new FileInputStream("src/main/resources/com/example/gui_final_project/textures/brown_cube.png")); //Номер ресурса: 0
    private Image wheat_cube = new Image(new FileInputStream("src/main/resources/com/example/gui_final_project/textures/wheat_cube.png")); //1
    private Image brick_cube = new Image(new FileInputStream("src/main/resources/com/example/gui_final_project/textures/brick_cube.png")); //2
    private Image stone_cube = new Image(new FileInputStream("src/main/resources/com/example/gui_final_project/textures/stone_cube.png")); //3
    private Image glass_cube = new Image(new FileInputStream("src/main/resources/com/example/gui_final_project/textures/glass_cube.png")); //4

    private Image[] resources_options = {brown_cube, wheat_cube, brick_cube, stone_cube, glass_cube};

    public Image[] getResources_options() {
        return resources_options;
    }

    private ArrayList<Integer> resources_deck = new ArrayList<>(); //колода ресурсов, из которой назначаются кнопки

    private int[][] res_cords = new int[4][4]; // карта ресурсов

    public int[][] getRes_cords() {
        return res_cords;
    }

    public void setRes_cords(int posX, int posY, int resource_type) {
        res_cords[posX][posY] = resource_type;
    }

    public Resources() throws FileNotFoundException {
        for (int i=0; i<3; i++){ //в колоду добавляется по 3 ресурса каждого типа
            resources_deck.add(0);
            resources_deck.add(1);
            resources_deck.add(2);
            resources_deck.add(3);
            resources_deck.add(4);
        }
        Collections.shuffle(resources_deck); //колода перемешиваается
        for (int i=0; i<4;i++){ //всем клеткам поля задаётся значение -1 - пустая клетка
            for (int j=0; j<4; j++){
                res_cords[i][j]=-1;
            }
        }
    }

    public int getFirstResource(){ //вытаскивание ресурса из колоды ресурсов
        //выбранный ресурс перемещается в конец колоды
        resources_deck.add(resources_deck.get(0));
        resources_deck.remove(0);

        return resources_deck.get(14);




    }


}
