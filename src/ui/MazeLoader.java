package ui;
import javax.swing.* ;
//import javax.swing.filechooser.*;
import java.awt.event.* ;

public class MazeLoader extends JButton implements ActionListener {
    private final MazeApp mazeApp;
    private String file="../data/labyrinth_1.maze";
    private JFileChooser chooser = new JFileChooser("../data");

    public MazeLoader(MazeApp mazeApp){
        super("Load the labyrinth");
        this.mazeApp = mazeApp;
        addActionListener(this);
    }
    public void setFile(){
        int flag = chooser.showOpenDialog(mazeApp);
        if (flag == JFileChooser.APPROVE_OPTION) 
            this.file=chooser.getSelectedFile().getAbsolutePath();
    }
    public final void actionPerformed(ActionEvent evt)
	{
        setFile();
        mazeApp.setCurrentPath(this.file);
        //we firstly hide the answer if it is showed.
		try{mazeApp.getMaze().initFromTextFile(file);
            mazeApp.setShowAnswer(false);}
        catch(Exception ex){System.out.println("can not initialise the maze.");}
	}
    public void setFile(String file){
        this.file=file;
    }
    public String getFile(){
        return this.file;
    }
}
