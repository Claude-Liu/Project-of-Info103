package ui;
import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel{
    public void notifyForUpdate(){
        
    }

    private final MazeInitializer mazeInitializer;
    private final AnswerIndicator answerIndicator;
    private final MazeSaver mazeSaver;

    public ButtonPanel (MazeApp mazeApp){
        setLayout(new GridLayout(1,3)) ; 
        add(mazeInitializer = new MazeInitializer(mazeApp));
        add(answerIndicator = new AnswerIndicator(mazeApp));
        add(mazeSaver = new MazeSaver(mazeApp));
    }
}
