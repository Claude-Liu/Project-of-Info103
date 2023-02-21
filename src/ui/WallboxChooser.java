package ui;
import javax.swing.* ;
import java.awt.event.* ;

public class WallboxChooser extends JButton implements ActionListener {
    private final MazeApp mazeApp;
    public WallboxChooser(MazeApp mazeApp){
        super("Wall");
        this.mazeApp=mazeApp;
        addActionListener(this);
    }
    public void notifyForUpdate(){
        setEnabled((mazeApp.getMaze().getSelectedHexagonIndice()!=-1));
    }
    public final void actionPerformed(ActionEvent evt){
        try{mazeApp.getMaze().changeBox(mazeApp.getMaze().getSelectedHexagonIndice(), 'W');}
        catch(Exception ex){ex.getStackTrace();}
    }

}
