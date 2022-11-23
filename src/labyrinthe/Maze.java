package labyrinthe;

import java.util.ArrayList;
import java.util.List;

import graph.*;
/**
 * 
 * @author llf
 * @attribute boxlist, size
 */
public class Maze implements Graph{
	private List<Mazebox> boxlist;
	private int size;

	
	public Maze() {
		boxlist = new ArrayList<Mazebox>();
		size = 0;
	}
	public Mazebox getbox(int i) {
		return boxlist.get(i);
	}
	public int getsize() {
		return size;
	}
	public List<Vertex> getAllVertexes(){
		List<Vertex> vertexlist = new ArrayList<Vertex>();
		for(Mazebox box: boxlist) {
			vertexlist.add(box);
		}
		return vertexlist;
	}

	public List<Vertex> getSuccessors(Vertex vertex){
		List<Vertex> vertexlist = new ArrayList<Vertex>();
		return vertexlist;
	}
	public int getDistance(Vertex src,Vertex dst) {
		int distance=0;
		return distance;
	}
}
