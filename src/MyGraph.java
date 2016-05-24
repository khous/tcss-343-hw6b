import java.util.*; 
import java.util.Map.Entry;

/**
 * A representation of a graph. Assumes that we do not have negative cost edges
 * in the graph.
 */
public class MyGraph implements Graph {
	// you will need some private fields to represent the graph
	// you are also likely to want some private helper methods
	
// private collections
	
	/* this is a map with each Vertex keyed to all of it's connected edges */
	private Map<Vertex,List<Edge>> myVrtEdgs = new HashMap<Vertex,List<Edge>>();
	
	/* this is a set of all Vertices in myGraph */
	private List<Vertex> myVrtCol = new LinkedList<Vertex>();
	
	/* this is a set of all Edges in myGraph */
	private List<Edge> myEdgCol = new LinkedList<Edge>();

	/**
	 * Creates a MyGraph object with the given collection of vertices and the
	 * given collection of edges.
	 * 
	 * @param v
	 *            a collection of the vertices in this graph
	 * @param e
	 *            a collection of the edges in this graph
	 */
	public MyGraph(Collection<Vertex> v, Collection<Edge> e) {
		
		colSetUp(v,e);

	}

	/**
	 * Return the collection of vertices of this graph
	 * 
	 * @return the vertices as a collection (which is anything iterable)
	 */
	@Override
	public Collection<Vertex> vertices() {
		return new LinkedList<Vertex>(myVrtCol);
	}

	/**
	 * Return the collection of edges of this graph
	 * 
	 * @return the edges as a collection (which is anything iterable)
	 */
	@Override
	public Collection<Edge> edges() {
		return new LinkedList<Edge>(myEdgCol);
	}

	/**
	 * Return a collection of vertices adjacent to a given vertex v. i.e., the
	 * set of all vertices w where edges v -> w exist in the graph. Return an
	 * empty collection if there are no adjacent vertices.
	 * 
	 * @param v
	 *            one of the vertices in the graph
	 * @return an iterable collection of vertices adjacent to v in the graph
	 * @throws IllegalArgumentException
	 *             if v does not exist.
	 */
	@Override
	public Collection<Vertex> adjacentVertices(Vertex v) {
		try{
			if(!myVrtCol.contains(v)){
				throw new IllegalArgumentException("Vertex " + v + " does not exist");
			}
		} catch (IllegalArgumentException q){
			System.out.println(q);
		}
		List<Vertex> adjVrt = new LinkedList<Vertex>();
		class EdgComp implements Comparator<Edge>{
			public int compare(Edge e1, Edge e2){
				return e1.getWeight() - e2.getWeight();
			}
		}
		PriorityQueue<Edge> order = new PriorityQueue<Edge>(new EdgComp());
		for(Entry<Vertex, List<Edge>> ent: myVrtEdgs.entrySet()){
			if(ent.getKey().equals(v)){
				for(Edge edg : ent.getValue()){
					order.add(edg);
				}
				while(order.size() > 0){
					adjVrt.add(order.poll().getDestination());
				}
				break;
			}
		}
		return adjVrt;
	}

	/**
	 * Test whether vertex b is adjacent to vertex a (i.e. a -> b) in a directed
	 * graph. Assumes that we do not have negative cost edges in the graph.
	 * 
	 * @param a
	 *            one vertex
	 * @param b
	 *            another vertex
	 * @return cost of edge if there is a directed edge from a to b in the
	 *         graph, return -1 otherwise.
	 * @throws IllegalArgumentException
	 *             if a or b do not exist.
	 */
	@Override
	public int edgeCost(Vertex a, Vertex b) {
		try{
			if(!myVrtCol.contains(a)){
				throw new IllegalArgumentException("Vertex " + a + " does not exist");
			}
			if(!myVrtCol.contains(b)){
				throw new IllegalArgumentException("Vertex " + b + " does not exist");
			}
		} catch (IllegalArgumentException q){
			System.out.println(q);
		}
		int cost = -1;
		for(Edge e : myVrtEdgs.get(a)){
			if (e.getDestination().equals(b) 
					&& (e.getWeight() < cost || cost == -1)){
				cost = e.getWeight();
			}
		}
		return cost;
	}

	/**
	 * Returns the shortest path from a to b in the graph, or null if there is
	 * no such path. Assumes all edge weights are nonnegative. Uses Dijkstra's
	 * algorithm.
	 * 
	 * @param a
	 *            the starting vertex
	 * @param b
	 *            the destination vertex
	 * @return a Path where the vertices indicate the path from a to b in order
	 *         and contains a (first) and b (last) and the cost is the cost of
	 *         the path. Returns null if b is not reachable from a.
	 * @throws IllegalArgumentException
	 *             if a or b does not exist.
	 */
	public Path shortestPath(Vertex a, Vertex b) {
		try{
			if(!myVrtCol.contains(a)){
				throw new IllegalArgumentException("Vertex " + a + " does not exist");
			}
			if(!myVrtCol.contains(b)){
				throw new IllegalArgumentException("Vertex " + b + " does not exist");
			}
		} catch (IllegalArgumentException q){
			System.out.println(q);
		}
		Path aPath;
		Map<Vertex, Path> DijMap;
		if (a.equals(b)){
			aPath = new Path(new LinkedList<Vertex>(), 0);
		} else {
			DijMap = dijkstra(a);
			aPath = DijMap.get(b);
			try{
				if(aPath.cost == Integer.MAX_VALUE){
					throw new IllegalArgumentException("there is no such path from " + a + " to " + b);
				}
			}
			catch(IllegalArgumentException q){
				System.out.println(q);
				aPath = null;
			}
		}
		return aPath;
	}

	
	
// private methods
	
	/**
	 * This is a private method for setting up a map of vertices to edges
	 * and two sets of both.
	 * 
	 * @param v = a collection or vertices
	 * @param e = a collection of Edges
	 */
	private void colSetUp(Collection<Vertex> v, Collection<Edge> e){
		
		for(Vertex vrt : v){
			try{
				if(myVrtCol.contains(vrt)){
					throw new IllegalArgumentException("Vertex [" + vrt + "] already in MyGraph");
				}else{
					myVrtEdgs.put(vrt, new LinkedList<Edge>());
					myVrtCol.add(vrt);
				}
			}catch(IllegalArgumentException q){
				System.out.println(q);
			}
			
		}
		for(Edge edg : e){
			Vertex start = edg.getSource();
			Vertex end = edg.getDestination();
			try {
				if(myVrtEdgs.containsKey(start) && myVrtEdgs.containsKey(end) 
						&& !(edg.getWeight() < 0)){
					if(!myEdgCol.contains(edg)){
						myVrtEdgs.get(start).add(edg);
						myEdgCol.add(edg);
					} else {
						throw new IllegalArgumentException("Edge [" + edg + "] is already in MyGraph");
				}

				}else{
					throw new IllegalArgumentException("this is not a legal edge " + edg);
				}
			} 
			catch(IllegalArgumentException q){
				System.out.println(q);
			}
		}
	}
	
	/**
	 * This method runs dijkstra's algorithm on the vertices collected in MyGraph
	 * and finds all the shortest paths from a starting vertex.
	 *  
	 * @param start = the vertex in the graph we start from
	 * @return aDijMap = a map of paths from the starting vertex to each other vertex
	 * 		(vertices unreachable will have a path length of Inerger.MAX_VALUE)
	 */
	private Map<Vertex, Path> dijkstra(Vertex start){
		Map<Vertex, Path> aDijMap = new HashMap<Vertex, Path>();
		List<Vertex> pending = new LinkedList<Vertex>();
		Set<Vertex> known = new HashSet<Vertex>();
		
		for(Vertex v: myVrtCol){
			List<Vertex> aPath = new LinkedList<Vertex>();
			aDijMap.put(v, new Path(aPath,Integer.MAX_VALUE));
		}
		List<Vertex> startPath = new LinkedList<Vertex>();
		startPath.add(start);
		aDijMap.put(start, new Path(startPath,0));
		
		pending.add(start);
		known.add(start);
		while(!pending.isEmpty()){
			Vertex next = pending.get(0);
			pending.remove(0);
			for(Vertex vrt: adjacentVertices(next)){
				if(!known.contains(vrt)){
					known.add(vrt);
					pending.add(vrt);
					if(edgeCost(next,vrt)+ aDijMap.get(next).cost < aDijMap.get(vrt).cost){
						List<Vertex> newPath = new LinkedList<Vertex>(aDijMap.get(next).vertices);
						newPath.add(vrt);
						int newCost = edgeCost(next,vrt) + aDijMap.get(next).cost;
						aDijMap.put(vrt, new Path(newPath ,newCost));
					}
				}
			}
		}
		return aDijMap;
	}
}
