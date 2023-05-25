package com.example.gui_final_project;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Bank extends NewBuilding {

    public Bank() throws FileNotFoundException {

        super();
        createModel();
        res_id = 6;
        this.Building_image =  new Image(new FileInputStream("src/main/resources/com/example/gui_final_project/textures/buildings_tiles/bank.png"));
    }

    @Override
    public void createModel() {
        Integer[] colors = {1, 1, 0, 4, 2};

        for (Integer cell : colors) {
            model.addVertex(cell);
        }
        model.addEdge(colors[0],colors[1]);
        model.addEdge(colors[0],colors[2]);
        model.addEdge(colors[1],colors[3]);
        model.addEdge(colors[2],colors[3]);
        model.addEdge(colors[3],colors[4]);

    }
}