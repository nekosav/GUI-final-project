package com.example.gui_final_project;

public class Shelter extends NewBuilding {

    public Shelter() {
        super();
        createModel();
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