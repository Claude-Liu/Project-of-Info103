package ui;
import javax.swing.* ;
import java.awt.event.* ;

public class DepartChooser extends JButton implements ActionListener {
    private final MazeApp mazeApp;
    public DepartChooser(MazeApp mazeApp){
        super("Departure");
        this.mazeApp=mazeApp;
        addActionListener(this);
    }
    public void notifyForUpdate(){
        setEnabled((mazeApp.getMaze().getSelectedHexagonIndice()!=-1)&(mazeApp.getMaze().getDepart()==null));
    }
    public final void actionPerformed(ActionEvent evt){
        try{mazeApp.getMaze().changeBox(mazeApp.getMaze().getSelectedHexagonIndice(), 'D');}
        catch(Exception ex){ex.getStackTrace();}
    }

}