import startercode.Edge;
import startercode.SimpleGraph;
import startercode.Vertex;

import java.util.*;
import java.io.*;

public class FindPaths {
	public static void main(String[] args) {
		SimpleGraph graph = generateGraph(args[0], args[1]);
		//while true read user input
	}

	public static Scanner readFile (String filePath) {
		Scanner s;
		try {
			s = new Scanner(new File(filePath));
		} catch(FileNotFoundException e1) {
			System.err.println("FILE NOT FOUND: " + filePath);
			System.exit(2);
			return null;
		}

		return s;
	}

	//Edges, vertices
	private static SimpleGraph generateGraph (String verticesFile, String edgesFile) {
		Scanner vScanner = readFile(verticesFile),
				eScanner = readFile(edgesFile);
		SimpleGraph outputGraph = new SimpleGraph();
		List<Vertex> vertices = new ArrayList<>();
		//Map the vertices for O(|V|) when building the graph
		HashMap<String, Vertex> vHash = new HashMap<>();

		while (vScanner.hasNext()) {
			Vertex v = outputGraph.insertVertex(new VertexData(), vScanner.next());
			vHash.put((String) v.getName(), v);//Save these for building the edges
		}

		while (eScanner.hasNext()) {
			String from = eScanner.next();
			String to = eScanner.next();

			Vertex fromV = vHash.get(from),
					toV = vHash.get(to);

			int weight = eScanner.nextInt();
			outputGraph.insertEdge(fromV, toV, new EdgeData(weight), "");
		}

		return outputGraph;
	}

	private Path dijkstra (SimpleGraph graph, AbstractVertexQueue queue, Vertex source) {
		Iterator<Vertex> vertexIterator = graph.vertices();
		//Initialize the vertices in the queue
		while (vertexIterator.hasNext()) {
			Vertex v = vertexIterator.next();
			queue.insert(v, Integer.MAX_VALUE);
			((VertexData)v.getData()).setPenultimateVertex(null);
		}

		VertexData vData = (VertexData)source.getData();
		vData.setDistFromSource(0);
		queue.decrease(source, vData, 0);
		int cardinalityOfVertices = graph.numVertices();
		for (int i = 0; i < cardinalityOfVertices; i++) {
			Vertex uStar = queue.deleteMin();
			VertexData uStarData = (VertexData) uStar.getData();
			uStarData.setKnown();

			Iterator adjEdges = graph.incidentEdges(uStar);
			while (adjEdges.hasNext()) {
				Edge adjacentEdge = (Edge) adjEdges.next();
				EdgeData adjacentEdgeData = (EdgeData) adjacentEdge.getData();
				//Determine which
				Vertex adjacentVertex = graph.opposite(uStar, adjacentEdge);
				VertexData adjacentVertexData = (VertexData) adjacentVertex.getData();
				if (adjacentVertexData.isKnown()) {//Prefer guard clause to arrow antipattern
					continue;
				}
				//Now that this is a truly unknown vertex, proceed
				int prospectiveNewDistanceFromSource = uStarData.getDistFromSource() + adjacentEdgeData.getWeight();
				if (prospectiveNewDistanceFromSource < adjacentVertexData.getDistFromSource()) {
					adjacentVertexData.setDistFromSource(prospectiveNewDistanceFromSource);
					adjacentVertexData.setPenultimateVertex(uStar);
					//DECREASE
					queue.decrease(adjacentVertex, adjacentVertexData, adjacentVertexData.getDistFromSource());
				}
			}
		}

		//backtrack path using the penultimate vertex, computing the distances between cities along the way

		return new Path(null, 0);
	}
}
