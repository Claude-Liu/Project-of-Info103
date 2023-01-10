package graph;
import java.util.ArrayList;
public class Dijkstra {
    private final static int inf=100000;

    public static Vertex findpivot(ArrayList<Vertex> all_vertexes, ProcessedVertexesImpl processedVertexes,MinDistanceImpl minDistance){
        ArrayList<Vertex> notprocessed_vertexes=new ArrayList<Vertex>();
        for (Vertex vertex:all_vertexes){
            if(!processedVertexes.contains(vertex)){notprocessed_vertexes.add(vertex);}
        }
        Vertex pivot=notprocessed_vertexes.get(0);
        for (int i=1;i<notprocessed_vertexes.size();i++){
            if(minDistance.get(pivot)>minDistance.get(notprocessed_vertexes.get(i))){pivot=notprocessed_vertexes.get(i);}
        }
        return pivot;
    }
	public static ShortestPath dijkstra(Graph graph,
                                        Vertex startVertex,
                                        Vertex endVertex){
        //Initialise
        ShortestPathImpl shortestPath=new ShortestPathImpl();
        ProcessedVertexesImpl processedVertexes = new ProcessedVertexesImpl();
        processedVertexes.add(startVertex);
        MinDistanceImpl minDistance=new MinDistanceImpl();
        minDistance.set(startVertex,0);
        ArrayList<Vertex> vertexes=(ArrayList<Vertex>)graph.getAllVertexes();
        for(Vertex vertex:vertexes){
            minDistance.set(vertex,inf);
        }
        ArrayList<Vertex> successors;
        Vertex pivot = startVertex;
        boolean flag=false;
        while(! processedVertexes.contains(endVertex)){
            successors=(ArrayList<Vertex>)graph.getSuccessors(pivot);
            for(Vertex suc:successors){
                //suc.print();
                if(!processedVertexes.contains(suc)){
                    int distance_tmp=minDistance.get(pivot)+graph.getDistance(pivot,suc);
                    //System.out.println(distance_tmp);
                    flag=minDistance.set(suc,distance_tmp);
                    //System.out.println(flag);
                    if (flag){shortestPath.previous(pivot,suc);}
                }
            }
            pivot=findpivot(vertexes,processedVertexes,minDistance);
            //System.out.println("");
            //pivot.print();
            //System.out.println("");
            processedVertexes.add(pivot);
        }
        return shortestPath;
    }
    
}
