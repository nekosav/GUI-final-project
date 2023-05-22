package com.example.gui_final_project;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Shelter extends NewBuilding {

    public Shelter() throws FileNotFoundException {
        super();
        createModel();
        res_id = 9;
        this.Building_image =  new Image(new FileInputStream("src/main/resources/com/example/gui_final_project/textures/buildings_tiles/" + res_id +".png"));
    }

    @Override
    public void createModel() {
        Integer[] colors = {3, 3, 4};

        for (Integer cell : colors) {
            model.addVertex(cell);
        }

        model.addEdge(colors[0],colors[1]);
        model.addEdge(colors[1],colors[2]);
    }
}