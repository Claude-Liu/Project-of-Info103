package labyrinthe;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import javax.swing.event.*;

import graph.Graph;
import graph.Vertex;
import graph.*;

import java.io.*;
import utils.Pair;
/**
 * This is a class to describe a maze 
 * @author llf
 * @attribute boxlist, size
 * this maze is of size 10*10
 */
public class Maze implements Graph{
	//the shape of the labyrinthe is fixed, so we store the mazeboxs in a 1-d arraylist
	private ArrayList<Vertex> boxlist;
	private int length;
	private int width;
	private ArrayList<ArrayList<Pair<Integer,Integer>>> AL;
	private DepartureBox depart;
	private ArrivalBox arrival;
	//inf means the distance is infini(not neighbour)
	private final static int inf=100000;

	public Maze (){
		this.boxlist = new ArrayList<Vertex>();
	}

	//we set the 2-d adjacence list here
	public final void setMaze() {
		int size=boxlist.size();
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
	//MVC
	private ArrayList<ChangeListener> listeners = new ArrayList<ChangeListener>() ;

	public void addObserver(ChangeListener listener) {
		listeners.add(listener) ;
	}

	public void stateChanges() {
		ChangeEvent evt = new ChangeEvent(this) ;
		for (ChangeListener listener : listeners) {
			listener.stateChanged(evt);
		}
	}
	//setters
	public void setAL(Mazebox src, Mazebox trg) {
		int label1=Integer.valueOf(src.getlabel()).intValue();
		int label2=Integer.valueOf(trg.getlabel()).intValue();
		Pair<Integer,Integer> pair = new Pair<Integer,Integer>(label1,label2);
		AL.get(label1).add(pair);
	}
	public void setDepart() throws Exception{
		int flag=0;
		for (Vertex box: boxlist){
			if (box.gettype().equals("depart")){
				flag+=1;
				this.depart = (DepartureBox)box;;
			}
		}
		System.out.println("100");
		if (flag==0){throw new Exception("There is no departure in the labytinthe");}
		if (flag>=2){throw new Exception("There are more than one departures in the labytinthe");}
		System.out.println("200");
	}
	public Vertex getDepart(){
		return this.depart;
	}
	public void setArrival() throws Exception{
		int flag=0;
		for (Vertex box: boxlist){
			if (box.gettype().equals("arrive")){
				flag++;
				this.arrival = (ArrivalBox)box;
			}
		}
		if (flag==0){throw new Exception("There is no arrival in the labytinthe");}
		if (flag>=2){throw new Exception("There are more than one arrivals in the labytinthe");}
	}
	public Vertex getArrival(){
		return this.arrival;
	}
	public void setBox (int i, Vertex mazebox) throws Exception{
		this.boxlist.set(i,mazebox);
		setMaze();
		setDepart();
		setArrival();
	}
	// getters
	public Vertex getbox(int i) {
		return boxlist.get(i);
	}
	public void setWidth(int width){
		this.width=width;
	}
	public int getWidth(){
		return this.width;
	}
	public void setLength(int length){
		this.length=length;
	}
	public int getLength(){
		return this.length;
	}
	public ArrayList<ArrayList<Pair<Integer,Integer>>> getAL(){
		return AL;
	}
	public void printPath(List<Vertex> path){
		for(Vertex vertex:path){
			System.out.println(String.format("(%d,%d)",((Mazebox)vertex).getposition()[0],((Mazebox)vertex).getposition()[1]));
		}
	}
	public List<Vertex> getAllVertexes(){
		ArrayList<Vertex> vertexlist = new ArrayList<Vertex>();
		for(Vertex box: boxlist) {
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
	public final void initFromTextFile(String fileName) throws Exception{
		String boxlist_label = "";
		try{
			FileReader rd = new FileReader(fileName);
			BufferedReader br = new BufferedReader(rd);
			String line = null;
			int width=0;
			while((line=br.readLine())!=null){
				setLength(line.length());
				boxlist_label=boxlist_label+line.strip();
				System.out.println(line);
				width+=1;
			}
			this.width=width;
			br.close();
		}
		catch(IOException e){e.printStackTrace();}
		for (int i=0;i<boxlist_label.length();i++){
			int y = i/this.length;
			int x = i-(this.length*y);
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
		}
		setMaze();
		setDepart();
		setArrival();
		stateChanges();
	}
	//call the dijkstra
	private ArrayList<Vertex> shortestPath;
	public void searchShortestPath(){
		ShortestPathImpl shortestPath_=(ShortestPathImpl) Dijkstra.dijkstra(this,this.depart,this.arrival);
		shortestPath= shortestPath_.getPath(depart,arrival);
		stateChanges();
	}
	public ArrayList<Vertex> getShortestPath(){
		return this.shortestPath;
	}
	public final void saveToTextFile(String fileName){

	}

	
}
