package ui;

import javax.swing.*;

public class FileMenu extends JMenu {
	private final QuitMenuItem quitMenuItem ;

   public FileMenu(MazeApp mazeApp)
   {
      super("File") ; 
      
      add(quitMenuItem = new QuitMenuItem(mazeApp)) ;
   }
}
