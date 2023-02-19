package ui;
import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel{
    public void notifyForUpdate(){
        mazeboxIndicator.notifyForUpdate();
    }

    private final MazeboxIndicator mazeboxIndicator;
    private final MazeboxChooser mazeboxChooser;
    private final MazeInitializer mazeInitializer;
    private final AnswerIndicator answerIndicator;

    public ButtonPanel (MazeApp mazeApp){
        setLayout(new GridLayout(1,4)) ; 
        add(mazeboxIndicator = new MazeboxIndicator(mazeApp));
        add(mazeboxChooser = new MazeboxChooser(mazeApp));
        add(mazeInitializer = new MazeInitializer(mazeApp));
        add(answerIndicator = new AnswerIndicator(mazeApp));
    }
}
