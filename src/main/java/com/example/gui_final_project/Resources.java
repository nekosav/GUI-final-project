package com.example.gui_final_project;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

public class Resources {

    private final Image wood = new Image(new FileInputStream("src/main/resources/com/example/gui_final_project/textures/resources_new/wood.png")); //Номер ресурса: 0
    private final  Image wheat = new Image(new FileInputStream("src/main/resources/com/example/gui_final_project/textures/resources_new/wheat.png")); //1
    private final  Image brick = new Image(new FileInputStream("src/main/resources/com/example/gui_final_project/textures/resources_new/brick.png")); //2
    private final  Image stone = new Image(new FileInputStream("src/main/resources/com/example/gui_final_project/textures/resources_new/stone.png")); //3
    private final  Image glass = new Image(new FileInputStream("src/main/resources/com/example/gui_final_project/textures/resources_new/glass.png")); //4

    private final Image wood_tile = new Image(new FileInputStream("src/main/resources/com/example/gui_final_project/textures/tiles_new/wood_tile.png")); //Номер тайла с ресурсом: 0
    private final  Image wheat_tile = new Image(new FileInputStream("src/main/resources/com/example/gui_final_project/textures/tiles_new/wheat_tile.png")); //1
    private final  Image brick_tile = new Image(new FileInputStream("src/main/resources/com/example/gui_final_project/textures/tiles_new/brick_tile.png")); //2
    private final  Image stone_tile = new Image(new FileInputStream("src/main/resources/com/example/gui_final_project/textures/tiles_new/stone_tile.png")); //3
    private final  Image glass_tile = new Image(new FileInputStream("src/main/resources/com/example/gui_final_project/textures/tiles_new/glass_tile.png")); //4

    private final Image[] resources_options = {wood, wheat, brick, stone, glass};

    private final Image[] resources_tiles_options = {wood_tile, wheat_tile, brick_tile, stone_tile, glass_tile};

    public Image[] getResources_options() {
        return resources_options;
    }

    public Image[] getResources_tiles_options() {return resources_tiles_options;}

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
