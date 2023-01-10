package labyrinthe;

public class WallBox extends Mazebox{
	private final boolean pass=false;
	
	public WallBox(Maze maze,int x,int y) {
		super(maze,x,y);
	}

	@Override
	public boolean getpass() {
		return this.pass;
	}
}