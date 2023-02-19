package labyrinthe;
import graph.Vertex;


public abstract class Mazebox implements Vertex{
	private int position_x;
	private int position_y;
	private boolean pass_;
	private Maze maze;//the maze that contain this mazebox
	protected String type;
	
	public Mazebox(Maze maze,int x,int y) {
		this.position_x=x;
		this.position_y=y;
		this.maze=maze;
		this.type="normal";
	}
	 
	public void print(){
		System.out.println(String.format("the position of the point is (%d,%d)",this.getposition()[0],this.getposition()[1]));
	}
	//setters
	public void setmaze(Maze maze){
		this.maze=maze;
	}
	//getters
	public String gettype(){
		return this.type;
	}
	public Maze getmaze(){
		return this.maze;
	}
	public int[] getposition() {
		int[] position = new int[2];
		position[0]=this.position_x;
		position[1]=this.position_y;
		return position;
	}
	public int label_calcul() {
		return this.position_y*this.maze.getLength()+this.position_x;
	}
	public String getlabel() {
		String label=Integer.toString(label_calcul());
		return label;
	}
	public boolean getpass(){
		return pass_;
	}
	public boolean equals(Vertex vertex){
		String label_1=this.getlabel();
		String label_2=vertex.getlabel();
		if (label_1.equals(label_2)) return true;
		else return false;
	} 
}


