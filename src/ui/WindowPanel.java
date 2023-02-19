package ui;

import javax.swing.*;
import java.awt.*;

public class WindowPanel extends JPanel {
	public void notifyForUpdate() 
	{
	   mazePanel.notifyForUpdate() ;
	   buttonPanel.notifyForUpdate() ;
	}
	private final MazePanel mazePanel;
	private final ButtonPanel buttonPanel;
	public WindowPanel(MazeApp mazeApp) {
		setLayout(new BorderLayout()) ;
		
		add(mazePanel = new MazePanel (mazeApp), BorderLayout.CENTER) ;
		add(buttonPanel = new ButtonPanel (mazeApp), BorderLayout.SOUTH) ;
	}
}
