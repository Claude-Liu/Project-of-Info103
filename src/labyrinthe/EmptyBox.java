package labyrinthe;

public class EmptyBox extends Mazebox{
	private final boolean pass=true;
	
	public EmptyBox(Maze maze,int x,int y) {
		super(maze,x,y);
	}
	
	@Override
	public boolean getpass() {
		return this.pass;
	}
}
