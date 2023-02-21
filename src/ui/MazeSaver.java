package ui;
import javax.swing.* ;
import java.awt.event.* ;

public class MazeSaver extends JButton implements ActionListener {
    private final MazeApp mazeApp;
    private String file="../data/labyrinthe.maze";

    public MazeSaver(MazeApp mazeApp){
        super("Save the labyrinth");
        this.mazeApp = mazeApp;
        addActionListener(this);
    }
    public final void actionPerformed(ActionEvent evt)
	{
        mazeApp.getMaze().saveToTextFile(file);
	}
    public void setFile(String file){
        this.file=file;
    }
    public String getFile(){
        return this.file;
    }
}
