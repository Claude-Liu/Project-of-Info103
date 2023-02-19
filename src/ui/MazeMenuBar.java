package ui;

import javax.swing.*;

public class MazeMenuBar extends JMenuBar {

	private final FileMenu fileMenu;
	
	public MazeMenuBar(MazeApp mazeApp) {
		super();
		add(fileMenu = new FileMenu(mazeApp)) ;
	}
}
