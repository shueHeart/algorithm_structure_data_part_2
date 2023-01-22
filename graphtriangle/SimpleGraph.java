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

	public ArrayList<Vertex> WeakVertices() {
		
		ArrayList<Vertex> weakVertexes = new ArrayList<Vertex>();
		
		for (int i = 0; i < max_vertex; ++i) {
			
			if (vertex[i] == null) {
				continue;
			}
			
			ArrayList<Integer> linkedVertexes = new ArrayList<Integer>();
			
			for (int g = 0; g < max_vertex; ++g) {
				
				if (i == g) continue;
				
				if (m_adjacency[i][g] == 1) {
					linkedVertexes.add(g);
					continue;
				}
				
				if (m_adjacency[g][i] == 1) {
					linkedVertexes.add(g);
				}
				
			}
			
			boolean enterInTriangle = false;
	
			for (Integer linkedVertex : linkedVertexes) {
				
				for (Integer linkedVertexSec : linkedVertexes) {
					
					if (linkedVertex == linkedVertexSec) {
						continue;
					}
					
					if (m_adjacency[linkedVertex][linkedVertexSec] == 1) {

						enterInTriangle = true;
						break;
						
					}
					
				}
				
				if (enterInTriangle) {
					break;
				}
				
			}

			if (!enterInTriangle) { 
				weakVertexes.add(vertex[i]);
			}
			
		}
		
		return weakVertexes;
	} 

}
