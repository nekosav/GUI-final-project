package com.example.gui_final_project;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Temple extends NewBuilding {

    public Temple() throws FileNotFoundException {
        super();
        createModel();
        res_id = 16;
        this.Building_image =  new Image(new FileInputStream("src/main/resources/com/example/gui_final_project/textures/buildings_tiles/temple.png"));
        this.info_image = new Image(new FileInputStream("src/main/resources/com/example/gui_final_project/textures/Cards/Temple.png"));
    }

    @Override
    public void createModel() {
        Integer[] colors = {0, 2, 3, 4};

        for (Integer cell : colors) {
            model.addVertex(cell);
        }
        model.addEdge(colors[0],colors[1]);
        model.addEdge(colors[1],colors[2]);
        model.addEdge(colors[2],colors[3]);

    }

    @Override
    public void count_points(Score score, int x, int y, int[][] cords){
        score.addTemp(y,x,cords);
    }
}