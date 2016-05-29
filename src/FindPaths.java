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
			Vertex v = outputGraph.insertVertex(null, vScanner.next());
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
}
