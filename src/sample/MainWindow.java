package sample;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    public MainWindow(){
        initUI();
    }

    private void initUI(){
        setTitle("Sorter");
        setSize(1000,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var m = new MainWindow();
            m.setVisible(true);
        });
    }
}
