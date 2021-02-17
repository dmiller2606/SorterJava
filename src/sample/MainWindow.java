package sample;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class MainWindow extends JFrame {

    public MainWindow(){
        initUI();
    }

    private void initUI(){
        setTitle("Sorter");
        setSize(1000,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //TODO: Add the actual UI
        Graph graph = new Graph();
        add(graph);

        // Testing stuff...
        Random r = new Random();
        List<Integer> data = r.ints(100,0,100).boxed().collect(Collectors.toList());
        graph.updateGraph(data,10);
        setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var m = new MainWindow();
        });
    }
}
