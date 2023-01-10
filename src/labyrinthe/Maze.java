package labyrinthe;
import java.util.ArrayList;
import java.util.List;

import graph.Graph;
import graph.Vertex;

import java.io.*;
import utils.Pair;
/**
 * 
 * @author llf
 * @attribute boxlist, size
 * this maze is of size 10*10
 */
public class Maze implements Graph{
	//the shape of the labyrinthe is fixed, so we store the mazeboxs in a 1-d arraylist
	private ArrayList<Mazebox> boxlist;
	private int size;
	private ArrayList<ArrayList<Pair<Integer,Integer>>> AL;
	private DepartureBox depart;
	private ArrivalBox arrival;
	//inf means the distance is infini(not neighbour)
	private final static int inf=100000;

	public Maze (int size){
		this.size=size;
		this.boxlist = new ArrayList<Mazebox>();
	}

	//we set the 2-d adjacence list here
	public final void setMaze(ArrayList<Mazebox> boxlist) {
		this.boxlist = boxlist;
		this.size=boxlist.size();
		AL = new ArrayList<ArrayList<Pair<Integer,Integer>>>();
		for(int i=0;i<size;i++) {
			ArrayList<Pair<Integer,Integer>> voisins=new ArrayList<Pair<Integer,Integer>>();
			for (int j=0;j<size;j++) {
				if (j!=i) {
					Vertex src=boxlist.get(i);
					Vertex trg=boxlist.get(j);
					if (getDistance(src,trg)!=inf) {
						Pair<Integer,Integer> voisin=new Pair<Integer,Integer>(i,j);
						voisins.add(voisin);
					}
				}
			}
			AL.add(voisins);
		}
	}
	
	//setters
	public void setsize(int size) {
		this.size=size;
	}
	public void setAL(Mazebox src, Mazebox trg) {
		int label1=Integer.valueOf(src.getlabel()).intValue();
		int label2=Integer.valueOf(trg.getlabel()).intValue();
		Pair<Integer,Integer> pair = new Pair<Integer,Integer>(label1,label2);
		AL.get(label1).add(pair);
	}
	public void setDepart(){
		int flag=0;
		for (Mazebox box: boxlist){
			if (box.gettype().equals("depart")){
				flag=1;
				this.depart = (DepartureBox)box;;
			}
		}
		assert(flag==1):"There is no departure in the labytinthe";
	}
	public void setArrival(){
		int flag=0;
		for (Mazebox box: boxlist){
			if (box.gettype().equals("arrive")){
				flag=1;
				this.arrival = (ArrivalBox)box;
			}
		}
		assert(flag==1):"There is no arrival in the labytinthe";
	}
	// getters
	public Mazebox getbox(int i) {
		return boxlist.get(i);
	}
	public int getsize() {
		return size;
	}
	public ArrayList<ArrayList<Pair<Integer,Integer>>> getAL(){
		return AL;
	}
	public Vertex getDepart(){
		return this.depart;
	}
	public Vertex getArrival(){
		return this.arrival;
	}
	public void printPath(List<Vertex> path){
		for(Vertex vertex:path){
			System.out.println(String.format("(%d,%d)",((Mazebox)vertex).getposition()[0],((Mazebox)vertex).getposition()[1]));
		}
	}
	public List<Vertex> getAllVertexes(){
		ArrayList<Vertex> vertexlist = new ArrayList<Vertex>();
		for(Mazebox box: boxlist) {
			vertexlist.add(box);
		}
		return vertexlist;
	}
	
	//we get the successors of a given vertex by query the adjacence list
	public List<Vertex> getSuccessors(Vertex vertex){
		ArrayList<Vertex> vertexlist = new ArrayList<Vertex>();
		Mazebox box = (Mazebox)vertex;
		int box_label = Integer.valueOf(box.getlabel()).intValue();
		ArrayList<Pair<Integer,Integer>> successors_lables= getAL().get(box_label);
		for (Pair<Integer,Integer> successor:successors_lables){
			int successor_label = successor.getValue();
			vertexlist.add(boxlist.get(successor_label));
		}
		return vertexlist;
	}
	public int getDistance(Vertex src,Vertex trg) {
		int distance=inf;
		Mazebox boxsrc = (Mazebox)src;
		Mazebox boxtrg = (Mazebox)trg;
		if (!(boxsrc.getpass() & boxtrg.getpass())){return distance;}
		int srcx = boxsrc.getposition()[0];
		int srcy = boxsrc.getposition()[1];
		int trgx = boxtrg.getposition()[0];
		int trgy = boxtrg.getposition()[1];
		if (trgy==srcy){
			if (trgx==srcx-1 | trgx==srcx+1){distance=1;}
		}
		if (trgy==srcy-1 | trgy==srcy+1){
			if (trgx==srcx | trgx==srcx+1){distance=1;}
		}
		return distance;
	}

	//We get the boxlist and set the AL in this fonction
	public final void initFromTextFile(String fileName){
		String boxlist_label = "";
		try{
			FileReader rd = new FileReader(fileName);
			BufferedReader br = new BufferedReader(rd);
			String line = null;
			while((line=br.readLine())!=null){
				boxlist_label=boxlist_label+line.strip();
			}
			System.out.println(boxlist_label);
			br.close();
		}
		catch(IOException e){e.printStackTrace();}
		assert (boxlist_label.length()!=100): "The size of the labyrinthe is not 10*10";
		for (int i=0;i<100;i++){
			int y = i/10;
			int x = i-(10*y);
			char mode = boxlist_label.charAt(i);
			Mazebox mazebox;
			switch (mode){
				case 'E':
					mazebox = new EmptyBox(this,x,y);
					break;
				case 'W':
					mazebox = new WallBox(this,x,y);
					break;
				case 'A':
					mazebox = new ArrivalBox(this, x, y);
					break;
				case 'D':
					mazebox = new DepartureBox(this, x, y);
					break;
				default:
					mazebox = new EmptyBox(this, x, y);
			}
			boxlist.add(mazebox);
			setMaze(boxlist);
			setDepart();
			setArrival();
		}
	}
	public final void saveToTextFile(String fileName){

	}

	
}
