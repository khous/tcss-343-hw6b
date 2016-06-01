package arrayImplementation;

import startercode.Edge;
import startercode.Vertex;

import java.util.List;

public class Array_Implementation {
	
	public static List<Vertex> vertexList;
	public static List<Edge> edgeList;
	
	public static List<VertexBucket> weights; 
	
	public static void arrayImplementation(Vertex theStart, Vertex theEnd) {
		boolean notFull = true;
		while (notFull) {
			boolean foundMissing = false;
			for (Vertex v : vertexList) {
				if (unknown(v)) foundMissing = true;
			}
			if (!foundMissing) notFull = false;
		}
		
	}

	private static boolean unknown(Vertex theVertex) {
		
		for (VertexBucket vb : weights) {
			if (vb.contains(theVertex)) return false;
		}
		
		return true;
	}
	
}
