package ui;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import labyrinthe.*;
import graph.*;
import utils.*;
import java.lang.Math;

public class MazePanel extends JPanel{
    public void notifyForUpdate(){
        setRadius();
        repaint();
    }

    private final MazeApp mazeApp;
    private final int panelWidth = 800;
    private final int panelHeight = 800;
    
    public void setRadius(){
        int mazeWidth = mazeApp.getMaze().getWidth();
        int mazeLength = mazeApp.getMaze().getLength();
        mazeApp.getMaze().setRadius(Math.min(panelWidth/(mazeLength*2),panelHeight/(mazeWidth*2))); 
    }

    private final static BasicStroke normalStroke ;
    private final static BasicStroke largeStroke ;
    static{normalStroke  = new BasicStroke(3.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER) ;}
    static{largeStroke  = new BasicStroke(8.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER) ;}
    
    public void paintHexagon(Graphics g, Polygon hexagon, Color color,boolean selected){
        Graphics2D g2 = (Graphics2D)g ;
        g2.setStroke(normalStroke) ; 
        if (selected) {g2.setStroke(largeStroke);System.out.println("selected"); }; 
        g2.drawPolygon(hexagon);
        g2.setColor(color);
		g2.fillPolygon(hexagon);
        g2.setColor(Color.BLACK);
    }
    public void paintHexagon(Graphics g,Pair<Polygon,Vertex> mapElement,boolean selected, boolean isPath){
        Polygon hexagon=mapElement.getKey();
        Mazebox box = (Mazebox) mapElement.getValue();
        boolean pass = box.getpass();
        String type = box.gettype();
        Color color;
        if (!isPath){
            color = Color.WHITE;
            if (!pass) color = Color.RED;
            if (type=="arrive")color=Color.BLUE;
            if (type=="depart")color=Color.ORANGE;
    }
        else{
            color = Color.GREEN;
            if (type=="arrive")color=Color.BLUE;
            if (type=="depart")color=Color.ORANGE;
        }
        paintHexagon(g, hexagon, color,selected);
    }
    public MazePanel(MazeApp mazeApp){
        this.mazeApp = mazeApp;
        setBackground(Color.WHITE) ;
		setPreferredSize(new Dimension(panelWidth,panelHeight)) ;

        MazeAppMouseListener mazeAppMouseListener
		   = new MazeAppMouseListener(mazeApp) ;
				
		addMouseListener       (mazeAppMouseListener) ;
		addMouseMotionListener (mazeAppMouseListener) ;
    }
    private boolean AinList(Pair<Polygon,Vertex> element,ArrayList<Pair<Polygon,Vertex>> list){
        for (Pair<Polygon,Vertex> e:list){
            if (element.getValue().equals(e.getValue())){return true;}
        }
        return false;
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        mazeApp.getMaze().setMazeMap();
        ArrayList<Pair<Polygon,Vertex>> hexagonsPath = null;
        ArrayList<Pair<Polygon,Vertex>> mazeMap =  mazeApp.getMaze().getMazeMap();
        for (Pair<Polygon,Vertex> mapElement: mazeMap){
            paintHexagon(g,mapElement,false,false);
        }
        if (mazeApp.getShowAnswer()){
            ArrayList<Vertex> shortestPath=mazeApp.getMaze().getShortestPath();
            hexagonsPath =  mazeApp.getMaze().path2Hexagons(shortestPath);
            for (Pair<Polygon,Vertex> mapElement: hexagonsPath){
                paintHexagon(g,mapElement,false,true);
            }
        }
        //there will be an exception if the indice is -1, this get means there is no selected hexagon.
        try{Pair<Polygon,Vertex> mapElementSelected=mazeMap.get(mazeApp.getMaze().getSelectedHexagonIndice());
            if (hexagonsPath==null) {paintHexagon(g,mapElementSelected,true,false);}
            else{
                if ((AinList(mapElementSelected,hexagonsPath))) paintHexagon(g,mapElementSelected,true,true);
                if (!(AinList(mapElementSelected,hexagonsPath)))paintHexagon(g,mapElementSelected,true,false);
        }
        }
        catch(Exception ex){//just pass
        }
    }
    
}
