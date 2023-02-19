package ui;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import labyrinthe.*;
import graph.*;
import java.lang.Math;

public class MazePanel extends JPanel{
    public void notifyForUpdate(){
        setRadius();
        repaint();
    }

    private final MazeApp mazeApp;
    private final int panelWidth = 800;
    private final int panelHeight = 800;
    private final int marginX = 20;
    private final int marginY = 20;
    private int radius;
    
    public void setRadius(){
        int mazeWidth = mazeApp.getMaze().getWidth();
        int mazeLength = mazeApp.getMaze().getLength();
        this.radius=Math.min(panelWidth/(mazeLength*2),panelHeight/(mazeWidth*2));
    }

    public int getRadius(){
        return radius;
    }
    private final static BasicStroke largeStroke ;
    static{largeStroke  = new BasicStroke(3.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER) ;}
    public void PaintHexagonal(Graphics g, int centerx, int centery, Color color){
        Graphics2D g2 = (Graphics2D)g ;
        g2.setStroke(largeStroke) ; 
        Polygon hexagon = new Polygon();
        hexagon.addPoint(centerx,centery+radius);
        hexagon.addPoint((int)(centerx+radius*Math.cos(Math.PI/6)),(int)(centery+radius*Math.sin(Math.PI/6)));
        hexagon.addPoint((int)(centerx+radius*Math.cos(Math.PI/6)),(int)(centery-radius*Math.sin(Math.PI/6)));
        hexagon.addPoint(centerx,centery-radius);
        hexagon.addPoint((int)(centerx-radius*Math.cos(Math.PI/6)),(int)(centery-radius*Math.sin(Math.PI/6)));
        hexagon.addPoint((int)(centerx-radius*Math.cos(Math.PI/6)),(int)(centery+radius*Math.sin(Math.PI/6)));
        g2.drawPolygon(hexagon);
        g2.setColor(color);
		g2.fillPolygon(hexagon);
        g2.setColor(Color.BLACK);
    }
    public MazePanel(MazeApp mazeApp){
        this.mazeApp = mazeApp;
        setBackground(Color.WHITE) ;
		setPreferredSize(new Dimension(panelWidth,panelHeight)) ;
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        ArrayList<Vertex> boxlist = (ArrayList<Vertex>) mazeApp.getMaze().getAllVertexes();
        for (Vertex box: boxlist){
            Mazebox current = (Mazebox)box;
            int centerX = 0;
            if (current.getposition()[1]%2==0){centerX=(int)((1+2*current.getposition()[0])*Math.cos(Math.PI/6)*this.radius)+marginX;}
            else{centerX=(int)((2+2*current.getposition()[0])*Math.cos(Math.PI/6)*this.radius)+marginX;}
            int centerY = (int)(this.radius*(1+(Math.sin(Math.PI/6))*3*current.getposition()[1]))+marginY;
            boolean pass = current.getpass();
            String type = current.gettype();
            Color color = Color.WHITE;
            if (!pass) color = Color.RED;
            if (type=="arrive")color=Color.BLUE;
            if (type=="depart")color=Color.ORANGE;
            PaintHexagonal(g, centerX, centerY, color);
        }
        if (mazeApp.getShowAnswer()){
            ArrayList<Vertex> shortestPath=mazeApp.getMaze().getShortestPath();
            for (Vertex box : shortestPath){
                Mazebox current = (Mazebox)box;
                int centerX = 0;
                if (current.getposition()[1]%2==0){centerX=(int)((1+2*current.getposition()[0])*Math.cos(Math.PI/6)*this.radius)+marginX;}
                else{centerX=(int)((2+2*current.getposition()[0])*Math.cos(Math.PI/6)*this.radius)+marginX;}
                int centerY = (int)(this.radius*(1+(Math.sin(Math.PI/6))*3*current.getposition()[1]))+marginY;
                Color color = Color.GREEN;
                String type = current.gettype();
                if (type=="arrive")color=Color.BLUE;
                if (type=="depart")color=Color.ORANGE;
                PaintHexagonal(g, centerX, centerY, color);
            }
        }
    }
}
