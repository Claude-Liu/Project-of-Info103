package labyrinthe;

public class DepartureBox extends Mazebox{
	private final boolean pass=true;
	public DepartureBox(Maze maze,int x, int y) {
		super(maze, x, y);	
		this.type = "depart";	
	}
	public boolean getpass() {
		return this.pass;
	}
}
