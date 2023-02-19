package ui;
import javax.swing.* ;
import java.awt.event.* ;

public class MazeInitializer extends JButton implements ActionListener {
    private final MazeApp mazeApp;
    private String file="../data/labyrinthe.maze";

    public MazeInitializer(MazeApp mazeApp){
        super("Initialize from the file");
        this.mazeApp = mazeApp;
        addActionListener(this);
    }
    public final void actionPerformed(ActionEvent evt)
	{
		try{mazeApp.getMaze().initFromTextFile(file);}
        catch(Exception ex){System.out.println("can not initialise the maze.");}
	}
    public void setFile(String file){
        this.file=file;
    }
    public String getFile(){
        return this.file;
    }
}
