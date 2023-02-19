package ui;
import javax.swing.*;
import java.awt.* ;

public class MazeboxIndicator extends JPanel {
    public void notifyForUpdate(){
        repaint();
    }

    private final MazeApp mazeApp;

    public MazeboxIndicator(MazeApp mazeApp){
        this.mazeApp=mazeApp;
        setPreferredSize(new Dimension(100,100)) ;
    }
}
