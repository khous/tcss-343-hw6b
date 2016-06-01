package arrayImplementation;

import startercode.Vertex;

import java.util.LinkedList;
import java.util.List;

public class VertexBucket {

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
	
}
