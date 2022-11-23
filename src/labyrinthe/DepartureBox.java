package labyrinthe;

public class DepartureBox extends Mazebox{
	private final boolean pass=true;
	public DepartureBox(int x, int y) {
		super(x, y);		
	}
	public boolean getpass() {
		return this.pass;
	}
}
