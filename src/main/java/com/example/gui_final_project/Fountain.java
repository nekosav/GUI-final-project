package com.example.gui_final_project;

public class Fountain extends NewBuilding {

    public Fountain() {
        super();
        createModel();
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