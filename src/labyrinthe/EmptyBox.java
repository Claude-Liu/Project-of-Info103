package labyrinthe;

public class EmptyBox extends Mazebox{
	private final boolean pass=true;
	
	public EmptyBox(int x,int y) {
		super(x,y);
	}
	
	public boolean getpass() {
		return this.pass;
	}
}
