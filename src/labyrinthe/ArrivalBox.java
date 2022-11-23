package labyrinthe;

public class ArrivalBox extends Mazebox{
	private final boolean pass=true;
	public ArrivalBox(int x, int y) {
		super(x, y);		
	}
	public boolean getpass() {
		return this.pass;
	}
}
