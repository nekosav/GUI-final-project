package com.example.gui_final_project;

public class Shop extends NewBuilding {

    public Shop() {
        super();
        createModel();
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