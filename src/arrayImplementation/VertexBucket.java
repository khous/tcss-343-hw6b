package arrayImplementation;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import dijkstra.AbstractVertexQueue;
import dijkstra.VertexData;
import startercode.Vertex;

public class VertexBucket extends AbstractVertexQueue{
 
	private LinkedList<Vertex>[] myListArr; // array of buckets (each index corresponds to the cost of the Vertices contained in each)
	private int numVrt;                     // the number of Vertices contained
	private int arrIndex;                   // the index of which to analyze unknown Vertices

	public VertexBucket(int arrSize){
		myListArr = new LinkedList[arrSize + 1];
		for(int i = 0; i < myListArr.length; i++){
			myListArr[i] = new LinkedList<>();
		}
		arrIndex = 0;
	}


	@Override
	public void decrease(Vertex v, VertexData newData, int dist) {
		VertexData vData = (VertexData)v.getData();
		int vIndex = vData.getIndexInQueue();
		List<Vertex> bucket = myListArr[vIndex];
		assert vIndex < dist;
		for (int i = 0 ; i < bucket.size(); i++){
			if ((bucket.get(i) == v)) {//Comparison of mem addresses is adequate
				v.setData(newData);
				newData.setIndexInQueue(dist);
				newData.setDistFromSource(dist);
				myListArr[dist].add(bucket.remove(i));//Add the vertex to the new bucket
				break;
			}
		}
	}

	@Override
	public Vertex deleteMin() {
		Vertex aVertex = null;
		while(myListArr[arrIndex].isEmpty() && arrIndex < myListArr.length){
			arrIndex++;
		}

		aVertex = myListArr[arrIndex].removeFirst();
		return aVertex;
	}

	@Override
	public int insert(Vertex v, int vDist) {
		int location = Math.min(vDist, myListArr.length-1);
		VertexData vData = (VertexData) v.getData();
		vData.setDistFromSource(location);
		vData.setIndexInQueue(location);
		myListArr[location].add(v);
		numVrt++;
		return location;
	}

	@Override
	public int getVertexCount() {
		return numVrt;
	}
	
}
