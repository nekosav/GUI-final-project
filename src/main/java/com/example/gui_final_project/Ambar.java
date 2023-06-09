package com.example.gui_final_project;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Ambar extends NewBuilding {


    public Ambar() throws FileNotFoundException {
        super();
        createModel();
        res_id = 13;
        this.Building_image =  new Image(new FileInputStream("src/main/resources/com/example/gui_final_project/textures/buildings_tiles/ambar.png"));
        this.info_image = new Image(new FileInputStream("src/main/resources/com/example/gui_final_project/textures/Cards/Ambar.png"));
    }

    @Override
    public void createModel() {
        Integer[] colors = {1, 1, 0, 2};

        for (Integer cell : colors) {
            model.addVertex(cell);
        }

        model.addEdge(colors[0],colors[1]);
        model.addEdge(colors[0],colors[2]);
        model.addEdge(colors[1],colors[3]);
        model.addEdge(colors[2],colors[3]);


    }
    @Override
    public void count_points(Score score, int x, int y, int[][] cords){
        score.addAmb(x,y,cords);
    }
}