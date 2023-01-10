package graph;
import java.util.HashMap;
import java.util.ArrayList;

public class ShortestPathImpl implements ShortestPath{
    private HashMap<Vertex,Vertex> shortestpath;

    public ShortestPathImpl(){
        this.shortestpath=new HashMap<Vertex,Vertex>();
    }
    public void previous(Vertex src,Vertex trg){
        if (shortestpath.containsKey(trg)){shortestpath.remove(trg);}
        shortestpath.put(trg,src);
    }
    public ArrayList<Vertex> getPath(Vertex dep, Vertex arr){
    	dep.print();
        ArrayList<Vertex> path=new ArrayList<Vertex>();
        path.add(arr);
        Vertex tmp=arr;
        while (!tmp.equals(dep)){
            path.add(shortestpath.get(tmp));
            tmp=shortestpath.get(tmp);
        }
        return path;
    }
    public Vertex get (Vertex vertex){
        return shortestpath.get(vertex);
    }
}