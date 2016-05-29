package heapimplementation;

import dijkstra.VertexData;
import startercode.Vertex;

/**
 * A heapimplementation.HeapNode has an integer key.
 * 
 * @author Donald Chinn
 * @version April 25, 2016
 */
public class HeapNode implements Comparable {
    private int key;            // the data
    private final Vertex vertex;
    /**
     * Constructor.
     * @param v the vertex to add to this heap
     */
    public HeapNode (Vertex v) {
        vertex = v;
        VertexData vData = (VertexData) v.getData();
        key = vData.getDistFromSource();
    }
    
    
    // Accessor methods
    public int getKey() {
        return key;
    }
    public void setKey(int newKey) {
        key = newKey;
    }

    /**
     * Implements the compareTo method.
     * @param rhs the other heapimplementation.HeapNode object.
     * @return 0 if two objects are equal;
     *     less than zero if this object is smaller;
     *     greater than zero if this object is larger.
     * @exception ClassCastException if rhs is not a heapimplementation.HeapNode.
     */
    public int compareTo (Object rhs) {
        if (this.key < ((HeapNode) rhs).key) {
            return -1;
        } else if (this.key == ((HeapNode) rhs).key) {
            return 0;
        } else {
            return 1;
        }
    }

    public Vertex getVertex() {
        return vertex;
    }
}