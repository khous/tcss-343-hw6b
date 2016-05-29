import startercode.Vertex;

import java.util.Collection;
import java.util.List;

public class Path {
	// we use public fields fields here since this very simple class is
	// used only for returning multiple results from shortestPath
	//TODO think about access control here, consider adding methods to return distance between path nodes etc
	public final Collection<Vertex> vertices;

	public Path(Collection<Vertex> vertices) {
		this.vertices = vertices;
	}
}
