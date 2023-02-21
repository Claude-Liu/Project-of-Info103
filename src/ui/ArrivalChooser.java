package ui;
import javax.swing.* ;
import java.awt.event.* ;

public class ArrivalChooser extends JButton implements ActionListener {
    private final MazeApp mazeApp;
    public ArrivalChooser(MazeApp mazeApp){
        super("Arrival");
        this.mazeApp=mazeApp;
        addActionListener(this);
    }
    public void notifyForUpdate(){
        setEnabled((mazeApp.getMaze().getSelectedHexagonIndice()!=-1)&(mazeApp.getMaze().getArrival()==null));
    }
    public final void actionPerformed(ActionEvent evt){
        try{mazeApp.getMaze().changeBox(mazeApp.getMaze().getSelectedHexagonIndice(), 'A');}
        catch(Exception ex){ex.getStackTrace();}
    }

}
