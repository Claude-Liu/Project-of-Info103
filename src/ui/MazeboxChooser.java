package ui;
import javax.swing.*;
import java.awt.*;

public class MazeboxChooser extends JPanel{

    public void notifyForUpdata(){
        wallboxChooser.notifyForUpdate();
        emptyboxChooser.notifyForUpdate();
        departChooser.notifyForUpdate();
        arrivalChooser.notifyForUpdate();
    }

    private final WallboxChooser wallboxChooser;
    private final EmptyboxChooser emptyboxChooser;
    private final DepartChooser departChooser;
    private final ArrivalChooser arrivalChooser;

    public MazeboxChooser(MazeApp mazeApp){
        setLayout(new GridLayout(1,4)) ; 
        add(wallboxChooser = new WallboxChooser(mazeApp));
        add(emptyboxChooser = new EmptyboxChooser(mazeApp));
        add(departChooser = new DepartChooser(mazeApp));
        add(arrivalChooser = new ArrivalChooser(mazeApp));
    }
}
