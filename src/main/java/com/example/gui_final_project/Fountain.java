package com.example.gui_final_project;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Fountain extends NewBuilding {

    public Fountain() throws FileNotFoundException {
        super();
        createModel();
        res_id = 8;
        this.Building_image =  new Image(new FileInputStream("src/main/resources/com/example/gui_final_project/textures/buildings_tiles/" + res_id +".png"));
    }

    @Override
    public void createModel() {
        Integer[] colors = {3, 0};

        for (Integer cell : colors) {
            model.addVertex(cell);
        }

        model.addEdge(colors[0],colors[1]);

    }
}