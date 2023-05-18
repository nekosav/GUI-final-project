package com.example.gui_final_project;

public class Cottage extends NewBuilding {

    public Cottage() {
        super();
        createModel();
    }

    @Override
    public void createModel() {
        Integer[] colors = {1, 4, 2};

        for (Integer cell : colors) {
            model.addVertex(cell);
        }

        model.addEdge(colors[0],colors[1]);
        model.addEdge(colors[1],colors[2]);
    }
}