/**
 * A binary minheap of HeapNode objects.
 * 
 * @author Donald Chinn
 * @version September 19, 2003
 */
package heapimplementation;

import dijkstra.AbstractVertexQueue;
import dijkstra.VertexData;
import startercode.Vertex;

public class DijkstraBinaryHeap extends AbstractVertexQueue {
    
    /* the heap is organized using the implicit array implementation.
     * Array index 0 is not used
     */
    private HeapNode[] elements;
    private int size;       // index of last element in the heap
    
    // Constructor
    public DijkstraBinaryHeap() {
        int initialCapacity = 10;
        
        this.elements = new HeapNode[initialCapacity + 1];
        this.elements[0] = null;
        this.size = 0;
    }

    public int getVertexCount() {
        return size;
    }
    
    /**
     * Constructor
     * @param capacity  number of active elements the heap can contain
     */    
    public DijkstraBinaryHeap(int capacity) {
        this.elements = new HeapNode[capacity + 1];
        this.elements[0] = null;
        this.size = 0;
    }
    
    
    /**
     * Given an array of HeapNodes, return a binary heap of those
     * elements.
     * @param data  an array of data (no particular order)
     * @return  a binary heap of the given data
     */
    public static DijkstraBinaryHeap buildHeap(HeapNode[] data) {
        DijkstraBinaryHeap newHeap = new DijkstraBinaryHeap(data.length);
        for (int i = 0; i < data.length; i++) {
            newHeap.elements[i+1] = data[i];
        }
        newHeap.size = data.length;
        for (int i = newHeap.size / 2; i > 0; i--) {
            newHeap.percolateDown(i);
        }
        return newHeap;
    }


    /**
     * Determine whether the heap is empty.
     * @return  true if the heap is empty; false otherwise
     */
    public boolean isEmpty() {
        return (size < 1);
    }

    /**
     *
     * @param v The vertex to emplace
     * @param distFromSource The new distance from source to set
     * @return The new index in this queue
     */
    public int insert(Vertex v, int distFromSource) {
        VertexData vData = (VertexData) v.getData();
        vData.setDistFromSource(distFromSource);
        HeapNode key = new HeapNode(v);

        if (size >= elements.length - 1) {
            // not enough room -- create a new array and copy
            // the elements of the old array to the new
            HeapNode[] newElements = new HeapNode[2*size];
            for (int i = 0; i < elements.length; i++) {
                newElements[i] = elements[i];
            }
            elements = newElements;
        }
        
        size++;
        elements[size] = key;
        return percolateUp(size);
    }

    public void decrease(Vertex v, VertexData vData, int newDistFromSource) {
        int indexInQueue = vData.getIndexInQueue();
        HeapNode hn = elements[indexInQueue];
        hn.setKey(newDistFromSource);
        percolateUp(indexInQueue);
    }
    
    /**
     * Remove the object with minimum key from the heap.
     * @return  the object with minimum key of the heap
     */
    public Vertex deleteMin() {
        if (isEmpty())
            return null;

        HeapNode returnValue = elements[1];
        elements[1] = elements[size];
        size--;
        percolateDown(1);
        return returnValue.getVertex();
    }
    
    
    /**
     * Given an index in the heap array, percolate that key up the heap.
     * @param index     an index into the heap array
     */
    private int percolateUp(int index) {
        HeapNode temp = elements[index];  // keep track of the item to be moved
        while (index > 1) {
            if (temp.compareTo(elements[index/2]) < 0) {
                //Put my parent down one level
                elements[index] = elements[index/2];
                VertexData parentData = (VertexData) elements[index].getVertex().getData();
                parentData.setIndexInQueue(index);
                index = index / 2;
            } else {
                break;
            }
        }
        //Finally put myself in the correct spot
        elements[index] = temp;

        VertexData vData = (VertexData) temp.getVertex().getData();
        vData.setIndexInQueue(index);
        return index;
    }
    
    
    /**
     * Given an index in the heap array, percolate that key down the heap.
     * @param index     an index into the heap array
     */
    private void percolateDown(int index) {
        int childIndex;
        HeapNode temp = elements[index];
        
        while (2*index <= size) {
            childIndex = 2 * index;
            if ((childIndex != size) &&
                (elements[childIndex + 1].compareTo(elements[childIndex]) < 0)) {
                childIndex++;
            }
            // ASSERT: at this point, elements[child] is the smaller of
            // the two children
            if (elements[childIndex].compareTo(temp) < 0) {
                elements[index] = elements[childIndex];
                VertexData parentData = (VertexData) elements[index].getVertex().getData();
                parentData.setIndexInQueue(index);
                index = childIndex;
            } else {
                break;
            }
        }

        ((VertexData)temp.getVertex().getData()).setIndexInQueue(index);
        elements[index] = temp;
    }
}
