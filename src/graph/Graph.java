package graph;

import java.util.List;

public interface Graph {
	public List<Vertex> getAllVertexes() ;
	public List<Vertex> getSuccessors(Vertex vertex) ;
	public int getDistance(Vertex src,Vertex trg) ;
}
