package ui;
import javax.swing.* ;
//import javax.swing.filechooser.*;
import java.awt.event.* ;
import java.io.*;
public class MazeCreator extends JButton implements ActionListener{
    private final MazeApp mazeApp;
    private String file="../data/labyrinth_new.maze";
    private JFileChooser chooser = new JFileChooser("../data");
    private int raw=10;
    private int column=10;

    public MazeCreator(MazeApp mazeApp) {
        super("Create a labyrinth");
        this.mazeApp = mazeApp;
        addActionListener(this);
    }
    public void setFile(){
        int flag = chooser.showSaveDialog(mazeApp);
        if (flag == JFileChooser.APPROVE_OPTION) 
            this.file=chooser.getSelectedFile().getAbsolutePath();
    }
    public void initializeMaze(){
        setFile();
        mazeApp.setCurrentPath(this.file);
        try{
            FileOutputStream fos = new FileOutputStream(this.file);
		    PrintWriter pw = new PrintWriter(fos);
            String rawElements="";
            for (int i=0;i<raw;i++){
                for(int j=0;j<column;j++){
                    rawElements+="E";
                }
                pw.println(rawElements);
                rawElements="";
            }
            pw.close();
        }
        catch(IOException ex){ex.printStackTrace();}
        try{mazeApp.getMaze().initFromTextFile(file);
            mazeApp.setShowAnswer(false);}
        catch(Exception ex){System.out.println("can not initialise the maze.");}
    }
    public void actionPerformed(ActionEvent evt){
        initializeMaze();
    }
}

