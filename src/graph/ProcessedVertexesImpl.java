package graph;
import java.util.HashSet;

public class ProcessedVertexesImpl implements ProcessedVertexes{
    private HashSet<Vertex> pro_vertexes;

    public ProcessedVertexesImpl (){
        this.pro_vertexes = new HashSet<Vertex>();
    }
    public void add(Vertex vertex){
        pro_vertexes.add(vertex);
    }
    public boolean contains(Vertex vertex){
        return  pro_vertexes.contains(vertex);
    }
}
