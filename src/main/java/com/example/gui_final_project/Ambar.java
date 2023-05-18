package com.example.gui_final_project;

public class Ambar extends NewBuilding {

    public Ambar() {
        super();
        createModel();
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
}