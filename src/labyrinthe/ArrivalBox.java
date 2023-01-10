package labyrinthe;

public class ArrivalBox extends Mazebox{
	private final boolean pass=true;
	public ArrivalBox(Maze maze, int x, int y) {
		super(maze,x, y);	
		this.type="arrive";	
	}
	public boolean getpass() {
		return this.pass;
	}
}
