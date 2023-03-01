package ui;
import javax.swing.*;
import labyrinthe.Maze;
import java.awt.event.* ;
public class QuitMenuItem extends JMenuItem implements ActionListener{
	private final MazeApp mazeApp;
	private final String file="../data/labyrinthe.maze";
	public QuitMenuItem(MazeApp mazeApp)
	{
		super("Quit") ;
		this.mazeApp = mazeApp ;
		addActionListener(this);
	}
	public void actionPerformed(ActionEvent evt)
   {
      Maze mazeModel = mazeApp.getMaze() ;
	   
      if (mazeModel.isModified()) {
         int response = JOptionPane.showInternalOptionDialog(this,
                                                             "Drawing not saved. Save it ?",
                                                             "Quit application",
                                                             JOptionPane.YES_NO_CANCEL_OPTION,
                                                             JOptionPane.WARNING_MESSAGE,
                                                             null,null,null) ;
		   switch (response) {
		   case JOptionPane.CANCEL_OPTION:
			   return ;
		   case JOptionPane.OK_OPTION:
			   mazeModel.saveToTextFile(file);
			   break ;
		   case JOptionPane.NO_OPTION:
			   break ;
		   }
	   }
	   System.exit(0) ;
   }
}
