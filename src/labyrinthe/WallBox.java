package labyrinthe;

public class WallBox extends Mazebox{
	private final boolean pass=false;
	
	public WallBox(int x,int y) {
		super(x,y);
	}
	
	public boolean getpass() {
		return this.pass;
	}
}