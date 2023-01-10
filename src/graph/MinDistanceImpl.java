package graph;
import java.util.HashMap;

public class MinDistanceImpl {
    private HashMap<Vertex,Integer> mindistances;

    public MinDistanceImpl(){
        this.mindistances=new HashMap<Vertex, Integer>();
    }
    public boolean set(Vertex vertex, int distance){
        if (mindistances.containsKey(vertex)){
            if(distance<mindistances.get(vertex)){
                mindistances.remove(vertex);
                Integer Distance = distance;
                mindistances.put(vertex,Distance);
                return true;
            }
        }
        else{
            Integer Distance = distance;
            mindistances.put(vertex,Distance);
            return true;
        }
        return false;
    }
    public int get(Vertex vertex){
        return (int)mindistances.get(vertex);
    }
}