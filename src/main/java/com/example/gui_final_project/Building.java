package com.example.gui_final_project;
import org.jgrapht.*;
import org.jgrapht.graph.*; // я тут стороннюю библиотеку скачал

import java.util.ArrayList;


public class Building { //класс для распознавания объектов
    // пока на примере амбара, когда будет норм работать перепишу под абстрактный, чтоб все здания от него наследовать
    DefaultUndirectedGraph<Integer, DefaultEdge> model = new DefaultUndirectedGraph<>(DefaultEdge.class);
    private Integer[] colors = {1,1,0,2};//цвета кубиков
    public Building() {


        for (Integer cell: colors){model.addVertex(cell);} // добавили все вершины в граф
        model.addEdge(colors[0],colors[1]); //грань между жёлтым и жёлтым2
        model.addEdge(colors[0],colors[2]); //грань между жёлтым и коричневым
        model.addEdge(colors[1],colors[3]); //грань между жёлтым2 и красным
        model.addEdge(colors[2],colors[3]); //грань между коричневым и жёлтым

        //System.out.println(model);
    }

    public boolean Graph_Compare(ArrayList<Cell> cells_to_check){
        //граф для распознаваемого объекта
        DefaultUndirectedGraph<Integer, DefaultEdge> graph_to_check = new DefaultUndirectedGraph<>(DefaultEdge.class);

        boolean graphs_are_equal = true;

        for (Cell cell: cells_to_check ) {graph_to_check.addVertex(cell.color);}//пихаем в него все переданные вершины

        for (Cell cell1: cells_to_check){ //создаём связи с соседними клетками
            for (Cell cell2: cells_to_check){
                if (Math.abs(cell1.x-cell2.x)+Math.abs(cell1.y-cell2.y)==1){//проверка на то, соседняя ли клетка
                                                                            //соседняя клетка - клетка справа, слева, снизу, сверху
                    graph_to_check.addEdge(cell1.color,cell2.color);
                }
            }
        }
        for (Integer cell: colors){//сравниваем граф с исходным
            for (Integer cell1: colors){
                if (!(graph_to_check.containsEdge(cell,cell1)== model.containsEdge(cell,cell1))){graphs_are_equal=false;}
            }
        }
        return graphs_are_equal;
        // метод сырой, но с амбаром работает, распознаёт как ни крути + зеркальные версии (что правилами позволяется)
        // с фигурами другой формы могут быть проблемы, нужны доп проверки
        // текущие проблемы:
        // 1. Главный трабл - графы хранят информацию в сетах -> одинаковые элементы идут нахуй (если добавить три вершины одного цвета, то в графе будет храниться только одна)
        // в принципе, работает, тк связи друг с другом добавляются, плюс связи с другими объектами заставляют работать нормально (хоть в амбаре есть два желтых кубика,
        // но метод работает корректно, тк связи с другими кубиками того требуют.)
        // 2. Графы умеют в связи, но не умеют в точные формы. Так что буква г и прямая для него одно и тоже.
        // В отличии от предыдущего, для этого есть решения. К примеру проверка на длину. С той же буквой г из 4 кубиков и прямой из тех 4 кубиков, проверка на макс(длина, ширина) == 3 решит проблему
        // Но эти проверки нужно писать отдельно для каждого объекта

    }
}
