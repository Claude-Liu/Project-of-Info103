package ui;
import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel{
    public void notifyForUpdate(){
        
    }

    private final MazeLoader mazeLoader;
    private final AnswerIndicator answerIndicator;
    private final MazeSaver mazeSaver;
    private final MazeCreator mazeCreator;

    public ButtonPanel (MazeApp mazeApp){
        setLayout(new GridLayout(1,4)) ;
        add(mazeCreator = new MazeCreator(mazeApp));
        add(mazeLoader = new MazeLoader(mazeApp));
        add(answerIndicator = new AnswerIndicator(mazeApp));
        add(mazeSaver = new MazeSaver(mazeApp));
    }
}
