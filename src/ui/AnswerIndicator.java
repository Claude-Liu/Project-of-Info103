package ui;
import javax.swing.* ;
import java.awt.event.* ;

public class AnswerIndicator extends JButton implements ActionListener{
    private final MazeApp mazeApp;

    public AnswerIndicator(MazeApp mazeApp){
        super("Answer");
        this.mazeApp=mazeApp;
        addActionListener(this);
    }
    public final void actionPerformed(ActionEvent evt)
	{
        mazeApp.setShowAnswer(true);;
		mazeApp.getMaze().searchShortestPath();
	}
}
