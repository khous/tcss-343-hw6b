package dijkstra;

import startercode.Vertex;

/**
 * Created by kyle on 5/29/2016.
 */
public class VertexData {
    private Vertex penultimateVertex;
    private int distFromSource = Integer.MAX_VALUE;
    private boolean known = false;
    private int indexInQueue;

    public Vertex getPenultimateVertex() {
        return penultimateVertex;
    }

    public void setPenultimateVertex(Vertex penultimateVertex) {
        this.penultimateVertex = penultimateVertex;
    }

    public int getDistFromSource() {
        return distFromSource;
    }

    public void setDistFromSource(int distFromSource) {
        this.distFromSource = distFromSource;
    }

    public void setKnown () { known = true; }

    public boolean isKnown() {
        return known;
    }

    public int getIndexInQueue() {
        return indexInQueue;
    }

    public void setIndexInQueue(int indexInQueue) {
        this.indexInQueue = indexInQueue;
    }
}
