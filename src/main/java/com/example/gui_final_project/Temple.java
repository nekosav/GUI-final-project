package com.example.gui_final_project;

public class Temple extends NewBuilding {

    public Temple() {
        super();
        createModel();
    }

    @Override
    public void createModel() {
        Integer[] colors = {2, 2, 3, 4};

        for (Integer cell : colors) {
            model.addVertex(cell);
        }
        model.addEdge(colors[0],colors[1]);
        model.addEdge(colors[1],colors[2]);
        model.addEdge(colors[2],colors[3]);

    }
}