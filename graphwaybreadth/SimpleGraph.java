import java.util.*;

class Vertex {
	public int Value;
	public boolean Hit;

	public Vertex(int val) {
		Value = val;
		Hit = false;
	}
}

class SimpleGraph {
	public Vertex[] vertex;
	public int[][] m_adjacency;
	public int max_vertex;

	public SimpleGraph(int size) {
		max_vertex = size;
		m_adjacency = new int[size][size];
		vertex = new Vertex[size];
	}

	public void AddVertex(int value) {

		for (int i = 0; i < max_vertex; ++i) {

			if (vertex[i] == null) {

				vertex[i] = new Vertex(value);
				break;

			}

		}

	}

	public void RemoveVertex(int v) {

		if (vertex[v] == null) {
			return;
		}

		for (int i = 0; i < max_vertex; ++i) {

			m_adjacency[v][i] = 0;
			m_adjacency[i][v] = 0;

		}

		vertex[v] = null;

	}

	public boolean IsEdge(int v1, int v2) {

		if (vertex[v1] == null || vertex[v2] == null) {
			return false;
		}

		return m_adjacency[v1][v2] == 1;

	}

	public void AddEdge(int v1, int v2) {

		if (vertex[v1] == null || vertex[v2] == null) {
			return;
		}

		m_adjacency[v1][v2] = 1;

	}

	public void RemoveEdge(int v1, int v2) {

		if (vertex[v1] == null || vertex[v2] == null) {
			return;
		}

		m_adjacency[v1][v2] = 0;

	}

	public ArrayList<Vertex> BreadthFirstSearch(int VFrom, int VTo) {

		for (Vertex vert : vertex) {
			vert.Hit = false;
		}
		
		Queue<Integer> queue = new Queue<Integer>();		
		
		ArrayList<Vertex> way = new ArrayList<Vertex>();
		
		way.add(vertex[VFrom]);
		
		BreadthFirstSearch(VFrom, VTo, queue, way);
		
		if (way.size() == 1) {
			way.remove(0);
		}
		
		return way;
		
	}
	
	private boolean BreadthFirstSearch(int VNow, int VTo, Queue<Integer> queue, ArrayList<Vertex> way) {
		
		vertex[VNow].Hit = true;
				
		for (int i = 0; i < max_vertex; ++i) {
			
			if (m_adjacency[VNow][i] == 0) {
				continue;
			}
			
			if (i == VTo) {
				
				way.add(vertex[i]);
				
				return true;
			}
			
			if (vertex[i].Hit) {
				continue;
			}
			
			queue.enqueue(i);
			
		}
		
		if (queue.size() == 0) {
			return false;
		}
		
		int dequeuedIndex = queue.dequeue();
		
		way.add(vertex[dequeuedIndex]);
		
		if (!BreadthFirstSearch(dequeuedIndex, VTo, queue, way)) {
			way.remove(vertex[dequeuedIndex]);
			return false;
		}

		return false;
		
	}

}

class Queue<T> {

	public List<T> storage;

	public Queue() {
		storage = new ArrayList<T>();
	}

	public void enqueue(T item) {
		storage.add(item);
	}

	public T dequeue() {

		if (storage.size() == 0)
			return null;

		return storage.remove(0);

	}

	public int size() {
		return storage.size();
	}

}
