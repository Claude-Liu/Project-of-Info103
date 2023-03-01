package ui;
import javax.swing.* ;
import java.awt.event.* ;

public class AnswerIndicator extends JButton implements ActionListener{
    private final MazeApp mazeApp;

    public AnswerIndicator(MazeApp mazeApp){
        super("'s Answer");
        this.setIcon(new ImageIcon("../data/IconRat.png"));
        this.mazeApp=mazeApp;
        addActionListener(this);
    }
    public final void actionPerformed(ActionEvent evt)
	{
        mazeApp.switchShowAnswer();
        if (mazeApp.getShowAnswer()==true) mazeApp.getMaze().searchShortestPath();
	}
}
