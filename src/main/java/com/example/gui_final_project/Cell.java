package com.example.gui_final_project;

public class Cell { //проходной вспомогательный класс, спокойно можно обойтись без него
    Integer color;
    Integer x,y;

    public Cell(Integer color, Integer x, Integer y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "color=" + color +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
