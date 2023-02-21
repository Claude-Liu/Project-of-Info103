package ui;
import java.awt.event.*;

public class MazeAppMouseListener extends MouseAdapter{
    private final MazeApp mazeApp;

    public MazeAppMouseListener(MazeApp mazeApp){
        this.mazeApp=mazeApp;
    }

    //mouseListener
    @Override
    public void mouseClicked(MouseEvent e){
        System.out.println(String.format("mouse clicked (%d,%d)",e.getX(),e.getY()));
        mazeApp.getMaze().setSelectedHexagon(e.getX(),e.getY());
    }
    
}
