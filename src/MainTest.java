import graph.*;
import labyrinthe.*;
import ui.*;
import java.util.ArrayList;


public class MainTest {
	public static void main(String[] args){
		Maze maze = new Maze();
		try{maze.initFromTextFile("../data/labyrinthe.maze");}
		catch(Exception ex){System.out.println("can not initialise the maze.");}
		DepartureBox depart = (DepartureBox) maze.getDepart();
		System.out.println(String.format("the position of departure point is (%d,%d)",depart.getposition()[0],depart.getposition()[1]));
		ArrivalBox arrive = (ArrivalBox) maze.getArrival();
		System.out.println(String.format("the position of arrival point is (%d,%d)",arrive.getposition()[0],arrive.getposition()[1]));
		System.out.println(depart.getlabel());
		System.out.println(String.format("The length of the maze is %d, and the width of the maze is %d",maze.getLength(),maze.getWidth()));
		Vertex departure=maze.getDepart();
		Vertex arrival=maze.getArrival();
		ShortestPathImpl shortestPath=new ShortestPathImpl();
		shortestPath=(ShortestPathImpl) Dijkstra.dijkstra(maze,departure,arrival);
		ArrayList<Vertex>path= shortestPath.getPath(departure,arrival);
		maze.printPath(path);
		
		//test the ui
		MazeApp mazeApp=new MazeApp();
		
	}
}
