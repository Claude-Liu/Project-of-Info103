package ui;
import javax.swing.* ;
import java.awt.event.* ;

public class MazeSaver extends JButton implements ActionListener {
    private final MazeApp mazeApp;
    private String file="../data/labyrinth_1.maze";

    public MazeSaver(MazeApp mazeApp){
        super("Save the labyrinth");
        this.mazeApp = mazeApp;
        addActionListener(this);
    }
    public final void actionPerformed(ActionEvent evt)
	{
        setFile(mazeApp.getCurrentPath());
        mazeApp.getMaze().saveToTextFile(file);
	}
    public void setFile(String file){
        this.file=file;
    }
    public String getFile(){
        return this.file;
    }
}
