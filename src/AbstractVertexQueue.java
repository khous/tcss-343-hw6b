import startercode.Vertex;

/**
 * Created by kyle on 5/29/2016.
 */
public abstract class AbstractVertexQueue {
    /**
     *
     * @param v The vertex to decrease
     * @param newData The new data to assign the vertex
     * @param dist The new distance to assign this vertex
     */
    public abstract void decrease (Vertex v, VertexData newData, int dist);

    /**
     * Get the minimum vertex for good ol' Dijkstra
     * @return The minimum dist vertex
     */
    public abstract Vertex deleteMin ();

    /**
     * This is how we can insert into our respective queues. This should probably return the index that it was logically
     * inserted into
     * @param v The vertex to emplace
     * @param vDist The dist of v to the source
     * @return The index or what ever significant value you want to return
     */
    public abstract int insert (Vertex v, int vDist);

    public abstract int getVertexCount ();
}
