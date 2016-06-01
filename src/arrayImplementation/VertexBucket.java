package arrayImplementation;

import java.util.LinkedList; 
import java.util.List;

import dijkstra.AbstractVertexQueue;
import dijkstra.VertexData;
import startercode.Vertex;

public class VertexBucket extends AbstractVertexQueue{

	private List<Vertex>[] listArray;
	private List<Vertex> vertices = new LinkedList<Vertex>();
	
	public void addVertex(Vertex theVertex) {
		vertices.add(theVertex);
	}
	
	public void removeVertex(Vertex theVertex) {
		vertices.remove(theVertex);
	}

	public boolean contains(Vertex theVertex) {
		if (vertices.contains(theVertex)) return true;
		return false;
	}

	@Override
	public void decrease(Vertex v, VertexData newData, int dist) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vertex deleteMin() {
		return vertices.remove(0);
	}

	@Override
	public int insert(Vertex v, int vDist) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getVertexCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
