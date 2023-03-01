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
	private int length; //the horizontal size of the maze
	private int width; //the vertical size of the maze
	private ArrayList<ArrayList<Pair<Integer,Integer>>> AL;
	private DepartureBox depart=null;
	private ArrivalBox arrival=null;
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
	private final int marginX = 20;
    private final int marginY = 20;
	private int radius;
	private ArrayList<Pair<Polygon,Vertex>> mazeMap;
	//-1 means there is no seleceted hexagon
	private int selectedHexagonIndice=-1;
	private boolean isModified=false;

	public void addObserver(ChangeListener listener) {
		listeners.add(listener) ;
	}
	public boolean isModified(){
		return isModified;
	}
	public void setModified(){
		this.isModified=true;
	}
	public void stateChanges() {
		setModified();
		ChangeEvent evt = new ChangeEvent(this) ;
		for (ChangeListener listener : listeners) {
			listener.stateChanged(evt);
		}
	}
	public void setSelectedHexagon(int x, int y){
		int indice=0;
		int flag=0;
		for (Pair<Polygon,Vertex> mazeElement:mazeMap){
			Polygon hexagon = mazeElement.getKey();
			if (hexagon.contains((double)x, (double)y)) {
				this.selectedHexagonIndice=indice;
				flag=1;
				break;}
			indice++;
		}
		if (flag==0) selectedHexagonIndice=-1;
		stateChanges();
	}
	public int getSelectedHexagonIndice(){
		return this.selectedHexagonIndice;
	}

	public void setRadius(int radius){
		this.radius=radius;
		//stateChanges();
	}
	public int getRadius(){
		return this.radius;
	}
	public Polygon getHexagon(Mazebox box){
		int centerX = 0;
		if (box.getposition()[1]%2==0){centerX=(int)((1+2*box.getposition()[0])*Math.cos(Math.PI/6)*this.radius)+marginX;}
		else{centerX=(int)((2+2*box.getposition()[0])*Math.cos(Math.PI/6)*this.radius)+marginX;}
		int centerY = (int)(this.radius*(1+(Math.sin(Math.PI/6))*3*box.getposition()[1]))+marginY;
		Polygon hexagon = new Polygon();
        hexagon.addPoint(centerX,centerY+radius);
        hexagon.addPoint((int)(centerX+radius*Math.cos(Math.PI/6)),(int)(centerY+radius*Math.sin(Math.PI/6)));
        hexagon.addPoint((int)(centerX+radius*Math.cos(Math.PI/6)),(int)(centerY-radius*Math.sin(Math.PI/6)));
        hexagon.addPoint(centerX,centerY-radius);
        hexagon.addPoint((int)(centerX-radius*Math.cos(Math.PI/6)),(int)(centerY-radius*Math.sin(Math.PI/6)));
        hexagon.addPoint((int)(centerX-radius*Math.cos(Math.PI/6)),(int)(centerY+radius*Math.sin(Math.PI/6)));
		return hexagon;
	}
	public void setMazeMap(){
		mazeMap = new ArrayList<Pair<Polygon,Vertex>>();
		for(Vertex box_: boxlist){
			Mazebox box = (Mazebox) box_;
			mazeMap.add(new Pair<Polygon,Vertex>(getHexagon(box),box));
		}
	}
	public ArrayList<Pair<Polygon,Vertex>> getMazeMap(){
		return this.mazeMap;
	}
	public ArrayList<Pair<Polygon,Vertex>> path2Hexagons(ArrayList<Vertex> path){
		ArrayList<Pair<Polygon,Vertex>> hexagons = new ArrayList<Pair<Polygon,Vertex>>();
		for(Vertex box_: path){
			Mazebox box = (Mazebox) box_;
			hexagons.add(new Pair<Polygon,Vertex>(getHexagon(box),box));
		}
		return hexagons;
	}
	//setters
	public void setDepart() throws Exception{
		int flag=0;
		depart=null;
		for (Vertex box: boxlist){
			if (box.gettype().equals("depart")){
				flag+=1;
				this.depart = (DepartureBox)box;;
			}
		}
		System.out.println("100");
		if (flag==0){System.out.println("There is no departure in the labytinthe");}
		if (flag>=2){throw new Exception("There are more than one departures in the labytinthe");}
		System.out.println("200");
		stateChanges();
	}
	public Vertex getDepart(){
		return this.depart;
	}
	public void setArrival() throws Exception{
		int flag=0;
		arrival=null;
		for (Vertex box: boxlist){
			if (box.gettype().equals("arrive")){
				flag++;
				this.arrival = (ArrivalBox)box;
			}
		}
		if (flag==0){System.out.println("There is no arrival in the labytinthe");}
		if (flag>=2){throw new Exception("There are more than one arrivals in the labytinthe");}
		stateChanges();
	}
	public Vertex getArrival(){
		return this.arrival;
	}
	/*we do not need to call stateChanges() here, 
	because it is already called in fonction setDepart and serArrival
	which are called in this fonction.
	*/
	public void changeBox (int i, char boxType) throws Exception{
		int x=((Mazebox)(boxlist.get(i))).getposition()[0];
		int y=((Mazebox)(boxlist.get(i))).getposition()[1];
		Mazebox mazebox;
			switch (boxType){
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
			if(srcy%2==0){
				if (trgx==srcx | trgx==srcx-1){distance=1;}}
			if(srcy%2==1){
				if (trgx==srcx | trgx==srcx+1){distance=1;}}
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
		//initialize the boxlist
		boxlist=new ArrayList<Vertex>();
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
		setMazeMap();
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
		try{
			FileOutputStream fos = new FileOutputStream(fileName);
			PrintWriter pw = new PrintWriter(fos);
			String mazeRaw = "";
			for (Vertex box_: boxlist){
				Mazebox box = (Mazebox) box_;
				String type = box.gettype();
				boolean pass = box.getpass();
				if (type=="depart"){
					mazeRaw+="D";
				}
				else if(type=="arrive"){
					mazeRaw+="A";
				}
				else{
					if (pass){
						mazeRaw+="E";
					}
					else{
						mazeRaw+="W";
					}
				}
				if (mazeRaw.length()==length){
					pw.println(mazeRaw);
					mazeRaw="";
				}
			}
			pw.close();
		}
		catch(IOException ex){ex.printStackTrace();}
	}
}
