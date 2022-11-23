package labyrinthe;
import graph.*;

public abstract class Mazebox implements Vertex{
	private int position_x;
	private int position_y;
	
	public Mazebox(int x,int y) {
		this.position_x=x;
		this.position_y=y;
	}
	public int[] getposition() {
		int[] position = new int[2];
		position[0]=this.position_x;
		position[1]=this.position_y;
		return position;
	}
	public int label_calcul() {
		return this.position_y*10+this.position_x;
	}
	public String getlabel() {
		String label=Integer.toString(label_calcul());
		return label;
	}
}
