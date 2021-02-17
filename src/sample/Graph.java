package sample;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Graph extends JPanel {
    private final int BORDER_GAP = 20;
    private int maxDataPoint;
    private double xScale,yScale;
    private int active;
    private List<Point> dataPoints;
    private List<Integer> data;

    public Graph(){
        active = -1;
        dataPoints = new ArrayList<>();
        data = new ArrayList<>();
        xScale = 0.0;
        yScale = 0.0;
        maxDataPoint = 0;
        setSize(1000,500);
        setBorder(BorderFactory.createLineBorder(Color.BLACK,2,true));
    }

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(1000,500);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if(data.size() == 0){
            return;
        }

        maxDataPoint = data.stream().max(Integer::compareTo).get();
        xScale = ((double) getWidth() - 2 * BORDER_GAP) / (data.size()-1);
        yScale = ((double) getHeight() - 2 * BORDER_GAP) / (maxDataPoint - 1);

        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(3f));

        dataPoints = IntStream.range(0,data.size())
                .mapToObj(a -> calculateGraphPoint(a,data.get(a)))
                .collect(Collectors.toList());

        for (Point p : dataPoints){
            g2.drawLine(p.x,p.y,p.x,getHeight());
        }
        if(active >= 0){
            g2.setColor(Color.red);
            Point activePoint = dataPoints.get(active);
            g2.drawLine(activePoint.x,activePoint.y, activePoint.x, getHeight());
        }
    }

    public void updateGraph(List<Integer> data, int active){
        if(this.data.size() != data.size()){
            this.data = data;
            this.active = active;
            repaint();
            return;
        }
        this.active = active;
        for(int i = 0; i < data.size(); i++){
            if(!data.get(i).equals(this.data.get(i))){
                int value = data.get(i);
                data.set(i,value);
                Point graphPoint = calculateGraphPoint(i,value);
                dataPoints.set(i,graphPoint);
                repaint(graphPoint.x, graphPoint.y, 5,value + 1);
            }
        }
    }

    public Point calculateGraphPoint(int x, int y){
        x = (int) (x * xScale + BORDER_GAP);
        y = (int) ((maxDataPoint - y) * yScale + BORDER_GAP);
        return new Point(x,y);
    }
}
