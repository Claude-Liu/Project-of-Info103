package ui;
import javax.swing.* ;
import java.awt.event.* ;

public class EmptyboxChooser extends JButton implements ActionListener {
    private final MazeApp mazeApp;
    public EmptyboxChooser(MazeApp mazeApp){
        super("Empty");
        this.mazeApp=mazeApp;
        addActionListener(this);
    }
    public void notifyForUpdate(){
        setEnabled((mazeApp.getMaze().getSelectedHexagonIndice()!=-1));
    }
    public final void actionPerformed(ActionEvent evt){
        try{mazeApp.getMaze().changeBox(mazeApp.getMaze().getSelectedHexagonIndice(), 'E');}
        catch(Exception ex){ex.getStackTrace();}
    }

}
