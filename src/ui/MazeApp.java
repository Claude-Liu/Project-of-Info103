package ui;

import javax.swing.*;
import javax.swing.event.*;
import java.util.ArrayList;
import labyrinthe.*;
/*
 * this class is a vue of the class Maze
 * @author Linfeng Liu
 */
public class MazeApp extends JFrame implements ChangeListener {
	
	public void stateChanged(ChangeEvent evt)
	{
	   windowPanel.notifyForUpdate() ;	
	}

    private final MazeMenuBar mazeMenuBar ;
    private final WindowPanel  windowPanel ;
	
	private Maze maze = new Maze();
    private boolean showAnswer = false;

    public void setShowAnswer(boolean ifshow){
        this.showAnswer=ifshow;
		if (ifshow==false) maze.stateChanges();
    }
	public void switchShowAnswer(){
		if (showAnswer==true){showAnswer=false;maze.stateChanges();}
		else {showAnswer=true;}
	}
    public boolean getShowAnswer(){
        return this.showAnswer;
    }
    public MazeApp() {
    	super("Maze App");
    	setJMenuBar(mazeMenuBar = new MazeMenuBar(this)) ;
		setContentPane(windowPanel = new WindowPanel(this)) ;
		
		this.maze.addObserver(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ; 
		pack() ;
		setVisible(true) ;
    }

	public Maze getMaze(){
		return this.maze;
	}
	public void setMaze(Maze maze){
		this.maze=maze;
	}
}
