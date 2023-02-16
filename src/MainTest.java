import graph.*;
import labyrinthe.*;
import java.util.ArrayList;

public class MainTest {
	public static void main(String[] args) {
		Maze maze = new Maze();
		maze.initFromTextFile("../data/labyrinthe.maze");
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
		/*
		Vertex vertex0 = maze.getbox(13);
		System.out.println("hhh");
		ArrayList<Vertex>successors =(ArrayList<Vertex>)maze.getSuccessors(vertex0);
		maze.printPath(successors);
		 
		Vertex vertex1 = maze.getbox(13);
		Vertex vertex2 = maze.getbox(4);
		System.out.println(maze.getDistance(vertex1, vertex2));
		*/
	}
}
