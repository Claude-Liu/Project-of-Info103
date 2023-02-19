package ui;
import javax.swing.*;

import labyrinthe.Maze;

public class MazeboxChooser extends JButton{
    private final MazeApp mazeApp;

    public MazeboxChooser(MazeApp mazeApp){
        super("choose box type");
        this.mazeApp = mazeApp;
    }
}
