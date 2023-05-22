package com.example.gui_final_project;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Shop extends NewBuilding {

    public Shop() throws FileNotFoundException {
        super();
        createModel();
        res_id = 10;
        this.Building_image =  new Image(new FileInputStream("src/main/resources/com/example/gui_final_project/textures/buildings_tiles/" + res_id +".png"));
    }

    @Override
    public void createModel() {
        Integer[] colors = {3, 4, 1, 3};

        for (Integer cell : colors) {
            model.addVertex(cell);
        }
        model.addEdge(colors[0],colors[1]);
        model.addEdge(colors[1],colors[2]);
        model.addEdge(colors[1],colors[3]);

    }
}